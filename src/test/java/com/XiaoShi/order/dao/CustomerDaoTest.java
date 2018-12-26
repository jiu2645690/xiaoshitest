package com.XiaoShi.order.dao;

import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.dao.AddressDao;
import com.xiaoshi.order.dao.CustomerDao;
import com.xiaoshi.order.dao.PictureDao;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private PictureDao pictureDao;

    @Test
    public void select() {
        Customer customer = customerDao.select(1L);
        assertNotNull(customer);
    }

    @Test
    public void getCustomerCount() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("nickName", "teswwwt1");
        map.put("email", "772855657@qq.com");
        assertNotNull(customerDao.getCustomerCount(map));
    }

    @Test
    public void insert() {
        Customer customer = new Customer();
        customer.setAddress(addressDao.select(1l));
        customer.setBalance(new BigDecimal("4.56"));
        customer.setCreatedAt(new Date());
        customer.setEmail("sdafdsa@sdaf.com");
        customer.setEmailIsValidated("1");
        customer.setFirstName("z1");
        customer.setLastName("z2");
        customer.setNickName("zwzw");
        customer.setPhoneNumber("12345678909");
        customer.setPhoneNumberIsValidated("1");
        customer.setSex(false);
        customer.setPicture(pictureDao.select(1l));
        customer.setWechatOpenid("32431253543");
        customer.setWechatOpenidIsValidated("1");
        customerDao.insert(customer);
        TestCase.assertTrue(customerDao.select(customer.getCustomerId()).getCustomerId().equals(customer.getCustomerId()));
    }
}