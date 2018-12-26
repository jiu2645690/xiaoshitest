package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.ShoppingCart;
import com.xiaoshi.order.service.ShoppingCartService;
import com.xiaoshi.order.service.CustomerService;
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
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ShoppingCartServiceTest {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CustomerService customerService;
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
        assertNotNull(shoppingCartService.get(1L));
    }

    @Test
    public void insertShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(customerService.get(11l));
        shoppingCartService.insertShoppingCart(shoppingCart);
        TestCase.assertTrue(shoppingCartService.get(1L).getShoppingCartId().equals(11L));
    }
}