package com.xiaoshi.order.service.imp;

import com.xiaoshi.order.dao.CustomerAuthDao;
import com.xiaoshi.order.pojo.entity.CustomerAuth;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.CustomerAuthService;
import com.xiaoshi.order.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  CustomerAuthServiceImpl
 **/
@Service
@Slf4j
public class CustomerAuthServiceImpl implements CustomerAuthService {

    @Autowired
    private CustomerAuthDao customerAuthDao;

    /**
     * 通过id获得CustomerAuth
     */
    @Override
    public CustomerAuth get(Long id) {
        return customerAuthDao.select(id);
    }

    /**
     * 获得CustomerAuth列表
     */
    @Override
    public List<CustomerAuth> getCustomerAuthList() {
        return customerAuthDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<CustomerAuth> list = customerAuthDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 CustomerAuth
     */
    @Override
    public void insertCustomerAuth(CustomerAuth customerAuth) {
        customerAuthDao.insert(customerAuth);
    }

    /**
     * 获得CustomerAuth通过Customer
     */
    @Override
    public CustomerAuth getCustomerByParameter(Long id) {
        return customerAuthDao.getCustomerByParameter(id);
    }

    /**
     * 更新CustomerAuth
     */
    @Override
    public void updateCustomerAuth(CustomerAuth customerAuth){
        customerAuthDao.updateCustomerAuth(customerAuth);
    }
}
