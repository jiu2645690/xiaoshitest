package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.StoreAuth;
import com.xiaoshi.order.service.OrderService;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.StoreAuthService;
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
import java.util.Date;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StoreAuthServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private StoreAuthService storeAuthService;
    @Autowired
    private ComboTemplateService comboTemplateService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PictureService pictureService;

    @Test
    public void get() {
        assertNotNull(storeAuthService.get(1L));
    }

    @Test
    public void insertStoreAuth() {
        StoreAuth storeAuth = new StoreAuth();
        storeAuth.setAllowLogin("1");
        storeAuth.setCreatedAt(new Date());
        storeAuth.setStore(storeService.get(1l));
        storeAuth.setStoreSecretkey("ewr344");
        storeAuth.setPassword("rwtertreytryut");
        storeAuthService.insertStoreAuth(storeAuth);
        TestCase.assertTrue(storeAuthService.get(storeAuth.getStoreAuthId()).getStoreAuthId().equals(storeAuth.getStoreAuthId()));
    }

    @Test
    public void updateStoreAuth() {
        StoreAuth storeAuth = storeAuthService.get(2L);
        storeAuth.setPassword("rwtertreytryut1");
        storeAuthService.updateStoreAuth(storeAuth);
        TestCase.assertTrue(storeAuthService.get(2L).getPassword().equals("rwtertreytryut1"));
    }
}