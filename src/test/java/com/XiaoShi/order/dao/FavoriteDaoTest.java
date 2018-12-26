package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.FavoriteDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.dao.CustomerDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.dao.ComboTemplateDao;
import com.xiaoshi.order.dao.FoodItemDao;
import com.xiaoshi.order.pojo.entity.Favorite;
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
public class FavoriteDaoTest {

    @Autowired
    private FavoriteDao favoriteDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private ComboTemplateDao comboTemplateDao;
    @Autowired
    private FoodItemDao foodItemDao;

    @Test
    public void select() {
        Favorite favorite = favoriteDao.select(1L);
        assertNotNull(favorite);
    }

    @Test
    public void insert() {
        Favorite favorite = new Favorite();
        favorite.setCustomerId(customerDao.select(1L));
        favorite.setStoreId(storeDao.select(1L));
        favorite.setFoodItemId(foodItemDao.select(1L));
        favorite.setComboTemplateId(comboTemplateDao.select(1L));
        favoriteDao.insert(favorite);
        TestCase.assertTrue(favoriteDao.select(1L).getFavoriteId().equals(favorite.getFavoriteId()));
    }

    @Test
    public void update() {
        Favorite favorite = favoriteDao.select(1L);
        favorite.setCustomerId(customerDao.select(2L));
        favoriteDao.updateFavorite(favorite);
        TestCase.assertTrue(favoriteDao.select(1L).getCustomerId().getCustomerId().equals(2L));
    }

}