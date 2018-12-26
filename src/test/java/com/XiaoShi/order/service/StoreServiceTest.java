package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.Order;
import com.xiaoshi.order.pojo.entity.Store;
import com.xiaoshi.order.service.OrderService;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.ComboTemplateService;
import com.xiaoshi.order.service.StoreService;
import com.xiaoshi.order.service.PictureService;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StoreServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private ComboTemplateService comboTemplateService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PictureService pictureService;

    @Test
    public void get() {
        assertNotNull(storeService.get(1L));
    }

    @Test
    public void insertStore() {
        Store store = new Store();
        store.setAverageScore(22.1);
        store.setBusinessAddress("test.test");
        store.setStar(5);
        store.setIntroduction("testtesttest");
        store.setStoreName("test");
        store.setBalance(new BigDecimal("4.56"));
        store.setDeleted(false);
        store.setEmail("sdafdsa@sdaf.com");
        store.setEmailIsValidated("1");
        store.setFirstName("z1");
        store.setLastName("z2");
        store.setNickName("zwzw");
        store.setPhoneNumber("12345678909");
        store.setPhoneNumberIsValidated("1");
        store.setSex(false);
        store.setPicture(pictureService.get(1l));
        store.setWechatOpenid("32431253543");
        store.setWechatOpenidIsValidated("1");
        storeService.insertStore(store);
        TestCase.assertTrue(storeService.get(store.getStoreId()).getStoreId().equals(store.getStoreId()));
    }

}