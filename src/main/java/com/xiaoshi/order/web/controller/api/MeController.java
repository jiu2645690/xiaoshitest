package com.xiaoshi.order.web.controller.api;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.entity.*;
import com.xiaoshi.order.pojo.form.TransactionForm;
import com.xiaoshi.order.service.*;
import com.xiaoshi.order.util.PageUtil;
import com.xiaoshi.order.util.ParameterCalibrationUtil;
import com.xiaoshi.order.util.ResultUtil;
import com.xiaoshi.order.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 商店Controller
 */
@RestController
@RequestMapping("/v1")
@EnableTransactionManagement
public class MeController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SalesSettingService salesSettingService;
    @Autowired
    private StoreAddressAssociationService storeAddressAssociationService;
    @Autowired
    private StorePictureAssociationService storePictureAssociationService;
    @Autowired
    private OperatingSettingService operatingSettingService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewPictureAssociationService reviewPictureAssociationService;
    private static String VERRIFICATIONCODE = "VERRIFICATIONCODE";

    /**
     * 编号：6-1-001,获取我的基本信息
     */
    @GetMapping("/me/customer")
    public ResponseEntity meCusomer(@RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success(customerService.getCustomerById(token));
    }

    /**
     * 编号：6-1-002,用户流水记录
     */
    @GetMapping("/wallet/transaction")
    public ResponseEntity walletTransaction(@RequestParam(value = "token", required = true) String token,
                                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("list:"+PageUtil.setPage(customerService.getwalletTransaction(token),PageUtil.setPage(pageNum,pageSize)).getList());
    }

    /**
     * 编号：6-2-006,获取我的基本信息
     */
    @GetMapping("/me/store")
    public ResponseEntity meStore(@RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success(storeService.getStoreById(token));
    }

    /**
     * 编号：6-1-003,绑定邮箱
     */
    @PutMapping("/customer/email")
    public ResponseEntity customerEmail(@RequestParam(value = "email", required = true) String email,
                                        @RequestParam(value = "token", required = true) String token,
                                        @RequestParam(value = "verifyCode", required = true) String verifyCode) {
        //验证redis中是否存在改校验码
        ParameterCalibrationUtil.IsMail(email);
        ParameterCalibrationUtil.IsEqualVerificationCode(VERRIFICATIONCODE + email, verifyCode);
        //获取用户
        Map map =new HashMap();
        map.put("email",email);
        if(customerService.getCustomerCount(map)>0)
            throw new OrderException(OrderCode.USERNAME_ALREADY_EXISTS);
        Customer customertemp = TokenUtil.getTokenModel(token).getCustomer();
        if(customertemp==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        customertemp.setEmail(email);
        return ResultUtil.success("status:" + customerService.updateCustomer(customertemp));
    }

    /**
     * 编号：6-1-004,绑定手机
     */
    @PutMapping("/customer/phone")
    public ResponseEntity customerPhone(@RequestParam(value = "phoneNumber", required = true) String phoneNumber,
                                        @RequestParam(value = "token", required = true) String token,
                                        @RequestParam(value = "verifyCode", required = true) String verifyCode) {
        //验证redis中是否存在改校验码
        ParameterCalibrationUtil.IsEqualVerificationCode(VERRIFICATIONCODE + phoneNumber, verifyCode);
        //获取用户
        Map map =new HashMap();
        map.put("phoneNumber",phoneNumber);
        if(customerService.getCustomerCount(map)>0)
            throw new OrderException(OrderCode.USERNAME_ALREADY_EXISTS);
        ParameterCalibrationUtil.IsPhone(phoneNumber);
        Customer customertemp = TokenUtil.getTokenModel(token).getCustomer();
        if(customertemp==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        customertemp.setPhoneNumber(phoneNumber);
        return ResultUtil.success("status:" + customerService.updateCustomer(customertemp));
    }

    /**
     * 编号：6-1-005,变更昵称
     */
    @PutMapping("/customer/nickname")
    public ResponseEntity customerNickname(@RequestParam(value = "token", required = true) String token,
                                           @RequestParam(value = "nickName", required = true) String nickName) {
        ParameterCalibrationUtil.IsEmpty(nickName);
        ParameterCalibrationUtil.IsEmpty(token);
        //判断用户名是否存在
        Map map =new HashMap();
        map.put("nickName",nickName);
        if(customerService.getCustomerCount(map)>0)
            throw new OrderException(OrderCode.USERNAME_ALREADY_EXISTS);
        Customer customertemp = TokenUtil.getTokenModel(token).getCustomer();
        if(customertemp==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        customertemp.setNickName(nickName);
        return ResultUtil.success("status:" + customerService.updateCustomer(customertemp));
    }

    /**
     * 编号：6-1-008,用户流水记录
     */
    @GetMapping("/wallet/storeTransaction")
    public ResponseEntity walletStoreTransaction(TransactionForm transactionForm) {
        return ResultUtil.success("list:"+customerService.getwalletStoreTransaction(transactionForm));
    }

    /**
     * 编号：6-1-007,用户分类资金统计
     */
    @GetMapping("/wallet/storeTransactionStatistics")
    public ResponseEntity walletStoreTransactionStatistics(@RequestParam(value = "token", required = true) String token,
                                                           @RequestParam(value = "year", required = true) Integer year,
                                                           @RequestParam(value = "month", required = true) Integer month) {
        return ResultUtil.success(customerService.getwalletStoreTransactionStatistics(token,year,month));
    }

    /**
     * 编号：6-2-009,绑定邮箱
     */
    @PutMapping("/sotre/email")
    public ResponseEntity sotreEmail(@RequestParam(value = "email", required = true) String email,
                                     @RequestParam(value = "token", required = true) String token,
                                     @RequestParam(value = "verifyCode", required = true) String verifyCode) {
        //验证redis中是否存在改校验码
        ParameterCalibrationUtil.IsMail(email);
        ParameterCalibrationUtil.IsEqualVerificationCode(VERRIFICATIONCODE + email, verifyCode);
        //获取用户
        Map map =new HashMap();
        map.put("email",email);
        if(storeService.getStoreCount(map)>0)
            throw new OrderException(OrderCode.USERNAME_ALREADY_EXISTS);
        Store storetemp = TokenUtil.getTokenModel(token).getStore();
        if(storetemp==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        storetemp.setEmail(email);
        return ResultUtil.success("status:" + storeService.updateStore(storetemp));
    }

    /**
     * 编号：6-1-010,绑定手机
     */
    @PutMapping("/sotre/phone")
    public ResponseEntity sotrePhone(@RequestParam(value = "phoneNumber", required = true) String phoneNumber,
                                     @RequestParam(value = "token", required = true) String token,
                                     @RequestParam(value = "verifyCode", required = true) String verifyCode) {
        //验证redis中是否存在改校验码
        ParameterCalibrationUtil.IsEqualVerificationCode(VERRIFICATIONCODE + phoneNumber, verifyCode);
        //获取用户
        Map map =new HashMap();
        map.put("phoneNumber",phoneNumber);
        if(storeService.getStoreCount(map)>0)
            throw new OrderException(OrderCode.USERNAME_ALREADY_EXISTS);
        ParameterCalibrationUtil.IsPhone(phoneNumber);
        Store storetemp = TokenUtil.getTokenModel(token).getStore();
        if(storetemp==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        storetemp.setPhoneNumber(phoneNumber);
        return ResultUtil.success("status:" + storeService.updateStore(storetemp));
    }

    /**
     * 编号：6-1-011,变更昵称
     */
    @PutMapping("/sotre/nickname")
    public ResponseEntity sotreNickname(@RequestParam(value = "token", required = true) String token,
                                        @RequestParam(value = "nickName", required = true) String nickName) {
        ParameterCalibrationUtil.IsEmpty(nickName);
        ParameterCalibrationUtil.IsEmpty(token);
        //判断用户名是否存在
        Map map =new HashMap();
        map.put("nickName",nickName);
        if(storeService.getStoreCount(map)>0)
            throw new OrderException(OrderCode.USERNAME_ALREADY_EXISTS);
        Store storetemp = TokenUtil.getTokenModel(token).getStore();
        if(storetemp==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        storetemp.setNickName(nickName);
        return ResultUtil.success("status:" + storeService.updateStore(storetemp));
    }

}
