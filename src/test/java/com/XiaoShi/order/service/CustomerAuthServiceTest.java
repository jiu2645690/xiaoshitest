package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.CustomerAuth;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.CustomerService;
import com.xiaoshi.order.service.CustomerAuthService;
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
public class CustomerAuthServiceTest {

    @Autowired
    private ComboService comboService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerAuthService customerAuthService;

    @Test
    public void get() {
        assertNotNull(comboService.get(1L));
    }

    @Test
    public void insertCustomerAuth() {
        CustomerAuth customerAuth = new CustomerAuth();
        customerAuth.setAllowLogin("1");
        customerAuth.setCreatedAt(new Date());
        customerAuth.setCustomer(customerService.get(1L));
        customerAuth.setCustomerSecretkey("ewr344");
        customerAuth.setPassword("rwtertreytryut");
        customerAuthService.insertCustomerAuth(customerAuth);
        TestCase.assertTrue(customerAuthService.get(customerAuth.getCustomerAuthId()).getCustomerAuthId().equals(customerAuth.getCustomerAuthId()));
    }

    @Test
    public void updateCustomerAuth() {
        CustomerAuth customerAuth = customerAuthService.get(1L);
        customerAuth.setCustomerSecretkey("ewr3445555");
        customerAuthService.updateCustomerAuth(customerAuth);
        TestCase.assertTrue(customerAuthService.get(1L).getCustomerSecretkey().equals("ewr3445555"));
    }
}