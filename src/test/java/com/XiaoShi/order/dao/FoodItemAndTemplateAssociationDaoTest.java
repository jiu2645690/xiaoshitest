package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.FoodItemAndTemplateAssociationDao;
import com.xiaoshi.order.dao.FoodItemDao;
import com.xiaoshi.order.dao.FoodItemTemplateDao;
import com.xiaoshi.order.pojo.entity.FoodItemAndTemplateAssociation;
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
public class FoodItemAndTemplateAssociationDaoTest {

    @Autowired
    private FoodItemAndTemplateAssociationDao foodItemAndTemplateAssociationDao;
    @Autowired
    private FoodItemDao foodItemDao;
    @Autowired
    private FoodItemTemplateDao foodItemTemplateDao;

    @Test
    public void select() {
        FoodItemAndTemplateAssociation foodItemAndTemplateAssociation = foodItemAndTemplateAssociationDao.select(1L);
        assertNotNull(foodItemAndTemplateAssociation);
    }

    @Test
    public void insert() {
        FoodItemAndTemplateAssociation foodItemAndTemplateAssociation = new FoodItemAndTemplateAssociation();
        foodItemAndTemplateAssociation.setFoodItem(foodItemDao.select(1L));
        foodItemAndTemplateAssociation.setFoodItemTemplate(foodItemTemplateDao.select(1L));
        foodItemAndTemplateAssociationDao.insert(foodItemAndTemplateAssociation);
        TestCase.assertTrue(foodItemAndTemplateAssociationDao.select(1L).getFoodItemTemplate().getFoodItemTemplateId().equals(1L));
    }

}