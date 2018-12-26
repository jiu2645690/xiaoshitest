package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.FoodItem;
import com.xiaoshi.order.service.ComboAndfFoodItemAssociationService;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.FoodItemService;
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
import java.math.BigDecimal;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FoodItemServiceTest {

    @Autowired
    private ComboAndfFoodItemAssociationService comboAndfFoodItemAssociationService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private ComboTemplateService comboTemplateService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PictureService pictureService;

    @Test
    public void get() {
        assertNotNull(foodItemService.get(1L));
    }

    @Test
    public void insertFoodItem() {
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
        foodItem.setStore(storeService.get(1L));
        foodItem.setPicture(pictureService.get(1L));
        foodItemService.insertFoodItem(foodItem);
        TestCase.assertTrue(foodItemService.get(foodItem.getFoodItemId()).getFoodItemId().equals(foodItem.getFoodItemId()));
    }

    @Test
    public void updateFoodItem() {
        FoodItem foodItem = foodItemService.get(1L);
        foodItem.setComplementaryIngredient("zwzw11");
        foodItemService.updateFoodItem(foodItem);
        TestCase.assertTrue(foodItemService.get(1L).getComplementaryIngredient().equals("zwzw11"));
    }
}