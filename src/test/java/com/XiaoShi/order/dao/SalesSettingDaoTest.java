package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.SalesSettingDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.pojo.entity.SalesSetting;
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
public class SalesSettingDaoTest {

    @Autowired
    private SalesSettingDao salesSettingDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private PictureDao pictureDao;

    @Test
    public void select() {
        SalesSetting salesSetting = salesSettingDao.select(1L);
        assertNotNull(salesSetting);
    }

    @Test
    public void insert() {
        SalesSetting salesSetting = new SalesSetting();
        salesSetting.setDinnerOrderendTime(new Date());
        salesSetting.setDinnerRefundInterval(new Date());
        salesSetting.setIsHaveDinner(true);
        salesSetting.setIsHaveLunch(true);
        salesSetting.setLunchOrderendTime(new Date());
        salesSetting.setLunchRefundInterval(new Date());
        salesSetting.setStore(storeDao.select(1L));
        salesSetting.setPicture(pictureDao.select(1L));
        salesSettingDao.insert(salesSetting);
        TestCase.assertTrue(salesSettingDao.select(salesSetting.getStore().getStoreId()).getStore().getStoreId().equals(1L));
    }

    @Test
    public void update() {
        SalesSetting salesSetting = salesSettingDao.select(1L);
        salesSetting.setPicture(pictureDao.select(2L));
        salesSetting.setStore(storeDao.select(1L));
        salesSettingDao.updateSalesSetting(salesSetting);
        TestCase.assertTrue(salesSettingDao.select(1L).getStore().equals(1L));
    }

}