package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.OperatingSettingDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.dao.FoodItemTemplateDao;
import com.xiaoshi.order.pojo.entity.OperatingSetting;
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
public class OperatingSettingDaoTest {

    @Autowired
    private OperatingSettingDao operatingSettingDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private FoodItemTemplateDao foodItemTemplateDao;

    @Test
    public void select() {
        OperatingSetting operatingSetting = operatingSettingDao.select(1L);
        assertNotNull(operatingSetting);
    }

    @Test
    public void insert() {
        OperatingSetting operatingSetting = new OperatingSetting();
        operatingSetting.setDate(new Date());
        operatingSetting.setStore(storeDao.select(1L));
        operatingSettingDao.insert(operatingSetting);
        TestCase.assertTrue(operatingSettingDao.select(operatingSetting.getStore().getStoreId()).getStore().getStoreId().equals(1L));
    }

    @Test
    public void update() {
        OperatingSetting operatingSetting = operatingSettingDao.select(1L);
        operatingSetting.setCurrentFoodItemTemplateId(foodItemTemplateDao.select(1L));
        operatingSettingDao.updateOperatingSetting(operatingSetting);
        TestCase.assertTrue(operatingSettingDao.select(1L).getStore().getStoreId().equals(1L));
    }

}