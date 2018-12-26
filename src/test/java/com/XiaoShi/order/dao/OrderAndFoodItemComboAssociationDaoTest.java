package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.ComboDao;
import com.xiaoshi.order.dao.FoodItemDao;
import com.xiaoshi.order.dao.OrderAndFoodItemComboAssociationDao;
import com.xiaoshi.order.dao.OrderDao;
import com.xiaoshi.order.pojo.entity.OrderAndFoodItemComboAssociation;
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
public class OrderAndFoodItemComboAssociationDaoTest {

    @Autowired
    private OrderAndFoodItemComboAssociationDao orderAndFoodItemComboAssociationDao;
    @Autowired
    private ComboDao comboDao;
    @Autowired
    private FoodItemDao foodItemDao;
    @Autowired
    private OrderDao orderDao;

    @Test
    public void select() {
        OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation = orderAndFoodItemComboAssociationDao.select(1L);
        assertNotNull(orderAndFoodItemComboAssociation);
    }

    @Test
    public void insert() {
        OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation = new OrderAndFoodItemComboAssociation();
        orderAndFoodItemComboAssociation.setCombo(comboDao.select(1L));
        orderAndFoodItemComboAssociation.setFoodItem(foodItemDao.select(1L));
        orderAndFoodItemComboAssociation.setOrder(orderDao.select(1L));
        orderAndFoodItemComboAssociationDao.insert(orderAndFoodItemComboAssociation);
        TestCase.assertTrue(orderAndFoodItemComboAssociationDao.select(orderAndFoodItemComboAssociation.getFoodItem().getFoodItemId()).getFoodItem().getFoodItemId().equals(orderAndFoodItemComboAssociation.getFoodItem().getFoodItemId()));
    }

    @Test
    public void update() {
        OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation = orderAndFoodItemComboAssociationDao.select(1L);
        orderAndFoodItemComboAssociation.setComboTemplateCount(1);
        orderAndFoodItemComboAssociation.setFoodItemCount(1);
        orderAndFoodItemComboAssociationDao.updateOrderAndFoodItemComboAssociation(orderAndFoodItemComboAssociation);
        TestCase.assertTrue(orderAndFoodItemComboAssociationDao.select(1L).getFoodItemCount().equals(1));
    }
}