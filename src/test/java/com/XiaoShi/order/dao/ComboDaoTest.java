package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.ComboTemplateDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.dao.ComboDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.pojo.entity.Combo;
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
public class ComboDaoTest {

    @Autowired
    private ComboDao comboDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private ComboTemplateDao comboTemplateDao;

    @Test
    public void select() {
        Combo combo = comboDao.select(1L);
        assertNotNull(combo);
    }

    @Test
    public void insert() {
        Combo combo = new Combo();
        combo.setComboTemplate(comboTemplateDao.select(1L));
        comboDao.insert(combo);
        TestCase.assertTrue(comboDao.select(1L).getComboTemplate().getComboTemplateId().equals(1));
    }

    @Test
    public void update() {
        Combo combo  = comboDao.select(1L);
        combo.setComboTemplate(comboTemplateDao.select(1L));
        comboDao.updateCombo(combo);
        TestCase.assertTrue(comboDao.select(1L).getComboTemplate().getComboTemplateId().equals(1));
    }

}