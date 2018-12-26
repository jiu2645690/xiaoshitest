package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.SalesSetting;
import com.xiaoshi.order.service.OrderService;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.FoodItemService;
import com.xiaoshi.order.service.SalesSettingService;
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
public class SalesSettingServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private SalesSettingService salesSettingService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PictureService pictureService;

    @Test
    public void get() {
        assertNotNull(salesSettingService.get(1L));
    }

    @Test
    public void insertSalesSetting() {
        SalesSetting salesSetting = new SalesSetting();
        salesSetting.setDinnerOrderendTime(new Date());
        salesSetting.setDinnerRefundInterval(new Date());
        salesSetting.setIsHaveDinner(true);
        salesSetting.setIsHaveLunch(true);
        salesSetting.setLunchOrderendTime(new Date());
        salesSetting.setLunchRefundInterval(new Date());
        salesSetting.setStore(storeService.get(1L));
        salesSetting.setPicture(pictureService.get(1L));
        salesSettingService.insertSalesSetting(salesSetting);
        TestCase.assertTrue(salesSettingService.get(1L).getStore().getStoreId().equals(1L));
    }

    @Test
    public void updateSalesSetting() {
        SalesSetting salesSetting = salesSettingService.get(1L);
        salesSetting.setPicture(pictureService.get(2L));
        salesSettingService.updateSalesSetting(salesSetting);
        TestCase.assertTrue(salesSettingService.get(1L).getPicture().getPictureId().equals(2L));
    }
}