package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.ShoppingCartLineItemDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.dao.FoodItemTemplateDao;
import com.xiaoshi.order.dao.ComboDao;
import com.xiaoshi.order.dao.FoodItemDao;
import com.xiaoshi.order.dao.ShoppingCartDao;
import com.xiaoshi.order.pojo.entity.ShoppingCartLineItem;
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
public class ShoppingCartLineItemDaoTest {

    @Autowired
    private ShoppingCartLineItemDao shoppingCartLineItemDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private FoodItemTemplateDao foodItemTemplateDao;
    @Autowired
    private ComboDao comboDao;
    @Autowired
    private FoodItemDao foodItemDao;
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Test
    public void select() {
        ShoppingCartLineItem shoppingCartLineItem = shoppingCartLineItemDao.select(1L);
        assertNotNull(shoppingCartLineItem);
    }

    @Test
    public void insert() {
        ShoppingCartLineItem shoppingCartLineItem = new ShoppingCartLineItem();
        shoppingCartLineItem.setUpdatedAt(new Date());
        shoppingCartLineItem.setStore(storeDao.select(1L));
        shoppingCartLineItem.setCreatedAt(new Date());
        shoppingCartLineItem.setDeliveryDate(new Date());
        shoppingCartLineItem.setDeliveryTime(1);
        shoppingCartLineItem.setState(2);
        shoppingCartLineItem.setComboCount(3L);
        shoppingCartLineItem.setFoodItemCount(4);
        shoppingCartLineItem.setCombo(comboDao.select(1L));
        shoppingCartLineItem.setFoodItem(foodItemDao.select(1L));
        shoppingCartLineItem.setShoppingCart(shoppingCartDao.select(1L));
        shoppingCartLineItemDao.insert(shoppingCartLineItem);
        TestCase.assertTrue(shoppingCartLineItemDao.select(shoppingCartLineItem.getShoppingCartLineItemId()).getShoppingCartLineItemId().equals(shoppingCartLineItem.getShoppingCartLineItemId()));
    }

    @Test
    public void update() {
        ShoppingCartLineItem shoppingCartLineItem  = shoppingCartLineItemDao.select(1L);
        shoppingCartLineItem.setState(88);
        shoppingCartLineItemDao.updateShoppingCartLineItem(shoppingCartLineItem);
        TestCase.assertTrue(shoppingCartLineItemDao.select(1L).getState().equals(88));
    }

}