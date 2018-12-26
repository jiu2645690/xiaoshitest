package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.FoodItemDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.pojo.entity.FoodItem;
import com.xiaoshi.order.pojo.entity.SalesSetting;
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
public class FoodItemDaoTest {

    @Autowired
    private FoodItemDao foodItemDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private PictureDao pictureDao;

    @Test
    public void select() {
        FoodItem foodItem = foodItemDao.select(1L);
        assertNotNull(foodItem);
    }

    @Test
    public void insert() {
        FoodItem foodItem = new FoodItem();
        foodItem.setComplementaryIngredient("zwzw");
        foodItem.setDailySpecialPrice(new BigDecimal("324"));
        foodItem.setDescription("zwzw");
        foodItem.setName("zwzw");
        foodItem.setIsAvailable(true);
        foodItem.setPrimaryIngredient("zwzw");
        foodItem.setRank(12);
        foodItem.setRemainingCount(12);
        foodItem.setPrice(new BigDecimal("324"));
        foodItem.setStore(storeDao.select(1L));
        foodItem.setPicture(pictureDao.select(1L));
        foodItemDao.insert(foodItem);
        TestCase.assertTrue(foodItemDao.select(foodItem.getFoodItemId()).getFoodItemId().equals(foodItem.getFoodItemId()));
    }

    @Test
    public void update() {
        FoodItem foodItem  = foodItemDao.select(1L);
        foodItem.setPrice(new BigDecimal("111111"));
        foodItemDao.updateFoodItem(foodItem);
        TestCase.assertTrue(foodItemDao.select(1L).getPrice().equals(new BigDecimal("111111")));
    }

}