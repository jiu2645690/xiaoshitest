package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.Favorite;
import com.xiaoshi.order.service.FavoriteService;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.FoodItemService;
import com.xiaoshi.order.service.CustomerService;
import com.xiaoshi.order.service.StoreService;
import com.xiaoshi.order.service.ComboTemplateService;
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
public class FavoriteServiceTest {

    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private ComboTemplateService comboTemplateService;

    @Test
    public void get() {
        assertNotNull(favoriteService.get(1L));
    }

    @Test
    public void insertFavorite() {
        Favorite favorite = new Favorite();
        favorite.setCustomerId(customerService.get(1L));
        favorite.setStoreId(storeService.get(1L));
        favorite.setFoodItemId(foodItemService.get(1L));
        favorite.setComboTemplateId(comboTemplateService.get(1L));
        favoriteService.insertFavorite(favorite);
        TestCase.assertTrue(favoriteService.get(favorite.getFavoriteId()).getFavoriteId().equals(favorite.getFavoriteId()));
    }

    @Test
    public void updateFavorite() {
        Favorite favorite = favoriteService.get(1L);
        favorite.setCustomerId(customerService.get(1L));
        favoriteService.updateFavorite(favorite);
        TestCase.assertTrue(favoriteService.get(1L).getCustomerId().getCustomerId().equals(1L));
    }
}