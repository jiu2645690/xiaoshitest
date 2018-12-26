package com.XiaoShi.order.dao;

import com.xiaoshi.order.pojo.entity.CustomerAuth;
import com.xiaoshi.order.dao.CustomerAuthDao;
import com.xiaoshi.order.dao.CustomerDao;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustomerAuthDaoTest {

    @Autowired
    private CustomerAuthDao customerAuthDao;
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void select() {
        CustomerAuth customerAuth = customerAuthDao.select(2L);
        assertNotNull(customerAuth);
    }

    @Test
    public void insert() {
        CustomerAuth customerAuth = new CustomerAuth();
        customerAuth.setAllowLogin("1");
        customerAuth.setCreatedAt(new Date());
        customerAuth.setCustomer(customerDao.select(1l));
        customerAuth.setCustomerSecretkey("ewr344");
        customerAuth.setPassword("rwtertreytryut");
        customerAuthDao.insert(customerAuth);
        TestCase.assertTrue(customerAuthDao.select(customerAuth.getCustomerAuthId()).getCustomerAuthId().equals(customerAuth.getCustomerAuthId()));
    }

}