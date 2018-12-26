package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.OrderDao;
import com.xiaoshi.order.dao.CustomerDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.pojo.entity.Order;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private PictureDao pictureDao;

    @Test
    public void select() {
        Order order = orderDao.select(1L);
        assertNotNull(order);
    }

    @Test
    public void insert() {
        Order order = new Order();
        order.setCreatedAt(new Date());
        order.setDeliveredTime(new Date());
        order.setRefundTime(new Date());
        order.setDeliveryDate(new Date());
        order.setLastUpdatedAt(new Date());
        order.setUuId("23edgtrfdb");
        orderDao.insert(order);
        TestCase.assertTrue(orderDao.select(1L).getOrderId().equals(order.getOrderId()));
    }

    @Test
    public void update() {
        Order order = orderDao.select(1L);
        order.setCustomer(customerDao.select(1L));
        orderDao.updateOrder(order);
        TestCase.assertTrue(orderDao.select(1L).getCustomer().getCustomerId().equals(1L));
    }

}