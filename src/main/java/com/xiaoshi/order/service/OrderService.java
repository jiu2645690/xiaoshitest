package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.dto.*;
import com.xiaoshi.order.pojo.entity.Order;
import com.xiaoshi.order.pojo.form.AddressAndStateForm;
import com.xiaoshi.order.pojo.form.OrderStoreForm;
import com.xiaoshi.order.pojo.form.OrderVerifyForm;
import com.xiaoshi.order.pojo.form.ReviewForm;
import com.xiaoshi.order.pojo.model.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order get(Long id);

    List<Order> getOrderList();

    PageModel search();

    void insertOrder(Order order);

    void updateOrder(Order order);

    List<Long> toOrder(List<Long> list);

    OrderInfo toOrderInfo(Long id);

    List<CombotList> toOrderCombotList(Long orderId, String token);

    List<OrderList> getOrderList(String token, Integer pageNum, Integer pageSize, Integer orderState);

    List<FoodItemName> fooditemStatistics(String token, Date date);

    List<FoodItemName> nowFooditemStatistics(String token);

    List<OrderStore> getOrderStoreList(OrderStoreForm orderStoreForm);

    Boolean updateOrderSum(Long orderId, Long comboId, Long foodItemId, Integer count);

    Boolean verifyOrder(String token, List<OrderVerifyForm> list);

    Boolean toReview(ReviewForm reviewForm);

    Boolean getOrderdelivery(String token, List<AddressAndStateForm> list);

    Boolean orderStateChange(String token, Integer state, Long orderId);

    Boolean orderSotreDelete(String token, Long orderId);

    Long oneMoreOrder(String token, Long orderId);
}
