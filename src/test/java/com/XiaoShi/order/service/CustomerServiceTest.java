package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void get() {
        assertNotNull(customerService.get(1L));
    }

    @Test
    public void search() {
        assertNotNull(customerService.search().getList());
    }

    @Test
    public void isLoginCustomerExist() {
        customerService.isLoginCustomerExist("email","209722371@qq.com");
        Customer customertemp=customerService.getCustomerByParameter("email", "209722371@qq.com");
        assertNotNull(customerService.search().getList());
    }
  
}