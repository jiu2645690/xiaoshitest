package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.Order;
import com.xiaoshi.order.service.OrderService;
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
import java.util.Date;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
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
        assertNotNull(orderService.get(1L));
    }

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setCreatedAt(new Date());
        order.setDeliveredTime(new Date());
        order.setRefundTime(new Date());
        order.setDeliveryDate(new Date());
        order.setLastUpdatedAt(new Date());
        order.setUuId("23edgtrfdb");
        orderService.insertOrder(order);
        TestCase.assertTrue(orderService.get(order.getOrderId()).getOrderId().equals(order.getOrderId()));
    }

    @Test
    public void updateOrder() {
        Order order = orderService.get(1L);
        order.setUuId("23edgtrfdb22");
        orderService.updateOrder(order);
        TestCase.assertTrue(orderService.get(1L).getUuId().equals("23edgtrfdb22"));
    }
}