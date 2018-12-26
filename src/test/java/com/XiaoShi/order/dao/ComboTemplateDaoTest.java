package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.ComboTemplateDao;
import com.xiaoshi.order.dao.FoodItemDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.pojo.entity.ComboTemplate;
import com.xiaoshi.order.pojo.entity.FoodItem;
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
public class ComboTemplateDaoTest {

    @Autowired
    private ComboTemplateDao comboTemplateDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private PictureDao pictureDao;

    @Test
    public void select() {
        ComboTemplate comboTemplate = comboTemplateDao.select(1L);
        assertNotNull(comboTemplate);
    }

    @Test
    public void insert() {
        ComboTemplate comboTemplate = new ComboTemplate();
        comboTemplate.setComboTemplateDiscount(333D);
        comboTemplate.setComboTemplateName("33333");
        comboTemplate.setComboTemplateNumber(345);
        comboTemplate.setComboTemplateRemainingCount(12);
        comboTemplate.setStore(storeDao.select(1L));
        comboTemplate.setPicture(pictureDao.select(1L));
        comboTemplateDao.insert(comboTemplate);
        TestCase.assertTrue(comboTemplateDao.select(1L).getComboTemplateId().equals(comboTemplate.getComboTemplateId()));
    }

    @Test
    public void update() {
        ComboTemplate comboTemplate   = comboTemplateDao.select(1L);
        comboTemplate.setComboTemplateNumber(444444);
        comboTemplateDao.updateComboTemplate(comboTemplate);
        TestCase.assertTrue(comboTemplateDao.select(1L).getComboTemplateNumber().equals(444444));
    }

}