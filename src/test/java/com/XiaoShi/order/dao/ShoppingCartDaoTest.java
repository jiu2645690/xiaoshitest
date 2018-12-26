package com.XiaoShi.order.dao;

import com.xiaoshi.order.pojo.entity.ShoppingCart;
import com.xiaoshi.order.dao.CustomerDao;
import com.xiaoshi.order.dao.ShoppingCartDao;
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
public class ShoppingCartDaoTest {

    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void select() {
        ShoppingCart shoppingCart = shoppingCartDao.select(1L);
        assertNotNull(shoppingCart);
    }

    @Test
    public void insert() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(customerDao.select(11l));
        shoppingCartDao.insert(shoppingCart);
        TestCase.assertTrue(shoppingCartDao.select(shoppingCart.getShoppingCartId()).getShoppingCartId().equals(shoppingCart.getShoppingCartId()));
    }


    @Test
    public void getShoppingCartByCustomerId() {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByCustomerId(customerDao.select(11l).getCustomerId());
        assertNotNull(shoppingCart);
    }

}