package com.xiaoshi.order.service.imp;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.dao.CustomerDao;
import com.xiaoshi.order.dao.TranscationDao;
import com.xiaoshi.order.dao.WalletDao;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.MeCustomer;
import com.xiaoshi.order.pojo.dto.TransactionStatistics;
import com.xiaoshi.order.pojo.dto.WalletTransaction;
import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.pojo.entity.Store;
import com.xiaoshi.order.pojo.entity.Transcation;
import com.xiaoshi.order.pojo.entity.Wallet;
import com.xiaoshi.order.pojo.form.TransactionForm;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.CustomerService;
import com.xiaoshi.order.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * CustomerServiceImpl
*/
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private WalletDao walletDao;
    @Autowired
    private TranscationDao transcationDao;

    /**
     *  通过id获取 Customer
     */
    @Override
    public Customer get(Long id) {
        return customerDao.select(id);
    }

    /**
     *  通过token获取 MeCustomer
     */
    @Override
    public MeCustomer getCustomerById(String token) {
        Customer customer = TokenUtil.getTokenModel(token).getCustomer();
        if (customer == null) throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Customer customertemp = customerDao.select(customer.getCustomerId());
        MeCustomer meCustomer =new MeCustomer();
        meCustomer.setBalance(customertemp.getBalance());
        meCustomer.setFirstName(customertemp.getFirstName());
        meCustomer.setLastName(customertemp.getLastName());
        if(customertemp.isSex()){
            meCustomer.setSex("男");
        }else{
            meCustomer.setSex("女");
        }
        meCustomer.setNickName(customertemp.getNickName());
        return meCustomer;
    }

    /**
     *  用户流水记录
     */
    @Override
    public List<WalletTransaction> getwalletTransaction(String token) {
        List<WalletTransaction> walletTransactions=new ArrayList<WalletTransaction>();
        Customer customer = TokenUtil.getTokenModel(token).getCustomer();
        if (customer == null) throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map = new HashMap();
        map.put("peopleType",1);
        map.put("peopleId",customer.getCustomerId());
        List<Wallet> wallets=walletDao.getWalletByParameter(map);
        if (wallets == null) return null;
        for(Wallet wallet:wallets){
            WalletTransaction walletTransaction=new WalletTransaction();
            walletTransaction.setAmount(wallet.getAmount());
            walletTransaction.setCreatedAt(wallet.getCreatedAt());
            walletTransaction.setWalleType(wallet.getWalleType().longValue());
            walletTransactions.add(walletTransaction);
        }
        return walletTransactions;
    }

    /**
     *  store流水记录
     */
    @Override
    public List<WalletTransaction> getwalletStoreTransaction(TransactionForm transactionForm) {
        List<WalletTransaction> walletTransactions=new ArrayList<WalletTransaction>();
        Store store = TokenUtil.getTokenModel(transactionForm.getToken()).getStore();
        if (store == null) throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map = new HashMap();
        map.put("peopleType",2);
        map.put("peopleId",store.getStoreId());
        if(transactionForm.getWalleType()!=null)
            map.put("walleType",transactionForm.getWalleType());
        Calendar calendar = Calendar.getInstance();
        calendar.set(transactionForm.getYear(), transactionForm.getMonth(), 0);
        int dayOfMonth =calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.set(transactionForm.getYear(), transactionForm.getMonth()-1, 1,0,0,0);
        map.put("frontDate",sdf.format(calendar.getTime()));
        calendar.set(transactionForm.getYear(), transactionForm.getMonth()-1, dayOfMonth,23,59,59);
        map.put("afterDate",sdf.format(calendar.getTime()));
        List<Wallet> wallets=walletDao.getWalletByParameter(map);
        if (wallets == null) return null;
        for(Wallet wallet:wallets){
            WalletTransaction walletTransaction=new WalletTransaction();
            walletTransaction.setAmount(wallet.getAmount());
            walletTransaction.setCreatedAt(wallet.getCreatedAt());
            walletTransaction.setWalleType(wallet.getWalleType().longValue());
            walletTransactions.add(walletTransaction);
        }
        return walletTransactions;
    }

    /**
     *  用户 分类资金统计
     */
    @Override
    public TransactionStatistics getwalletStoreTransactionStatistics(String token, Integer year, Integer month) {
        TransactionStatistics transactionStatisticstemp=new TransactionStatistics();
        Store store = TokenUtil.getTokenModel(token).getStore();
        if (store == null) throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        //营业额统计
        BigDecimal turnoverStatistics=new BigDecimal(0);
        //到账金额统计
        BigDecimal zhangPeriodStatistics=new BigDecimal(0);
        //提现金额统计
        BigDecimal withdrawalStatistics=new BigDecimal(0);
        //充值金额统计
        BigDecimal rechargeStatistics=new BigDecimal(0);
        //退款金额统计
        BigDecimal refundStatistics=new BigDecimal(0);
        Map map = new HashMap();
        map.put("storeId",store.getStoreId());
        map.put("isSucess",0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 0);
        int dayOfMonth =calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.set(year, month-1, 1,0,0,0);
        map.put("frontDate",sdf.format(calendar.getTime()));
        calendar.set(year, month-1, dayOfMonth,23,59,59);
        map.put("afterDate",sdf.format(calendar.getTime()));
        List<Transcation> transcations=transcationDao.getTranscationByParameter(map);
        if (transcations == null) return null;
        for(Transcation transcation:transcations){
            //流水类型：0 支付， 1提现 2充值 3退款
           if(transcation.getType()==0){
               //营业额统计
               if(transcation.getOrder()!=null)
                    turnoverStatistics=turnoverStatistics.add(transcation.getAmount());
               //到账金额统计
               if(transcation.getOrder()!=null)
                   if(transcation.getOrder().getZhangPeriod()==1)
                        zhangPeriodStatistics=zhangPeriodStatistics.add(transcation.getAmount());
           }
            if(transcation.getType()==1){
                withdrawalStatistics=withdrawalStatistics.add(transcation.getAmount());
            }
            if(transcation.getType()==2){
                rechargeStatistics=rechargeStatistics.add(transcation.getAmount());
            }
            if(transcation.getType()==3){
                refundStatistics=refundStatistics.add(transcation.getAmount());
            }
        }
        transactionStatisticstemp.setRechargeStatistics(rechargeStatistics);
        transactionStatisticstemp.setRefundStatistics(refundStatistics);
        transactionStatisticstemp.setTurnoverStatistics(turnoverStatistics);
        transactionStatisticstemp.setWithdrawalStatistics(withdrawalStatistics);
        transactionStatisticstemp.setZhangPeriodStatistics(zhangPeriodStatistics);
        return transactionStatisticstemp;
    }

    /**
     * 获取Customer列表
     */
    @Override
    public List<Customer> getCustomerList() {
        return customerDao.selectList();
    }

    /**
     *  分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<Customer> list = customerDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增Customer
     */
    @Override
    public void insertCustomer(Customer customer) {
        customerDao.insert(customer);
    }

    @Override
    public Boolean updateCustomer(Customer customer) {
        try {
            customerDao.updateCustomer(customer);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     *  获取Customer记录数
     */
    @Override
    public Integer getCustomerCount(Map<String, String> map) {
        return customerDao.getCustomerCount(map);
    }

    /**
     * 根据参数判断是否存在对应数据
     */
    @Override
    public void isCustomerExist(String key, String val) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, val);
        if (getCustomerCount(map) > 0) {
            throw new OrderException(OrderCode.LOGIN_NOT_SUCCESS);
        }
    }

    /**
     * 根据参数判断登录用户是否存在
     */
    public void isLoginCustomerExist(String key, String val) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, val);
        if (!(getCustomerCount(map) > 0)) {
            throw new OrderException(OrderCode.USER_NOT_EXISTS);
        }
    }

    /**
    *根据参数是否存在
    */
    public Boolean isCustomerVerifyExist(String key, String val) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, val);
        if (!(getCustomerCount(map) > 0)) {
            return false;
        }
        return true;
    }

    /**
     * 根据参数判断登录Wechat用户是否存在
     */
    @Override
    public void isWechatLoginCustomerExist(String key, String val) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, val);
        if (!(getCustomerCount(map) > 0)) {

        }
    }

    /**
     *  根据参数判断是否存在对应Customer
     */
    @Override
    public Customer getCustomerByParameter(String key, String val) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, val);
        return customerDao.getCustomerByParameter(map);
    }
}
