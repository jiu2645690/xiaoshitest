package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.ComboTemplateDao;
import com.xiaoshi.order.dao.FoodItemTemplateDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.pojo.entity.ComboTemplate;
import com.xiaoshi.order.pojo.entity.FoodItemTemplate;
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
public class FoodItemTemplateDaoTest {

    @Autowired
    private FoodItemTemplateDao foodItemTemplateDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private PictureDao pictureDao;

    @Test
    public void select() {
        FoodItemTemplate foodItemTemplate = foodItemTemplateDao.select(1L);
        assertNotNull(foodItemTemplate);
    }

    @Test
    public void insert() {
        FoodItemTemplate foodItemTemplate = new FoodItemTemplate();
        foodItemTemplate.setCreatedAt(new Date());
        foodItemTemplate.setFoodItemTemplateName("222");
        foodItemTemplateDao.insert(foodItemTemplate);
        TestCase.assertTrue(foodItemTemplateDao.select(foodItemTemplate.getFoodItemTemplateId()).getFoodItemTemplateId().equals(foodItemTemplate.getFoodItemTemplateId()));
    }

    @Test
    public void update() {
        FoodItemTemplate foodItemTemplate   = foodItemTemplateDao.select(1L);
        foodItemTemplate.setFoodItemTemplateName("qqq222");
        foodItemTemplateDao.updateFoodItemTemplate(foodItemTemplate);
        TestCase.assertTrue(foodItemTemplateDao.select(1L).getFoodItemTemplateName().equals("qqq222"));
    }

}