package com.XiaoShi.order.service;

import com.xiaoshi.order.dao.FoodItemDao;
import com.xiaoshi.order.dao.FoodItemTemplateDao;
import com.xiaoshi.order.pojo.entity.FoodItemAndTemplateAssociation;
import com.xiaoshi.order.service.FoodItemAndTemplateAssociationService;
import junit.framework.TestCase;
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
public class FoodItemAndTemplateAssociationServiceTest {

    @Autowired
    private FoodItemAndTemplateAssociationService foodItemAndTemplateAssociationService;
    @Autowired
    private FoodItemTemplateDao foodItemTemplateDao;
    @Autowired
    private FoodItemDao foodItemDao;

    @Test
    public void get() {
        assertNotNull(foodItemAndTemplateAssociationService.get(1L));
    }

    @Test
    public void search() {
        assertNotNull(foodItemAndTemplateAssociationService.search().getList());
    }

    @Test
    public void insertFoodItemAndTemplateAssociation() {
        FoodItemAndTemplateAssociation foodItemAndTemplateAssociation = new FoodItemAndTemplateAssociation();
        foodItemAndTemplateAssociation.setFoodItem(foodItemDao.select(1L));
        foodItemAndTemplateAssociation.setFoodItemTemplate(foodItemTemplateDao.select(1L));
        foodItemAndTemplateAssociationService.insertFoodItemAndTemplateAssociation(foodItemAndTemplateAssociation);
        TestCase.assertTrue(foodItemAndTemplateAssociationService.get(1L).getFoodItem().equals(1L));
    }
  
}