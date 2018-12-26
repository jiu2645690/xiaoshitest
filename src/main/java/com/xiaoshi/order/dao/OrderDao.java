package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Order;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderDao {
    /**
     * 通过id获取Order
     */
    Order select(Long id);
    /**
     * 删除Order
     */
    void deleteOrderById(Long id);
    /**
     * 新增Order
     */
    void insert(Order order);
    /**
     * 获取Order列表
     */
    List<Order> selectList();
    /**
     *批量新增 Order
     */
    void insertBatch(List<Order> orders);
    /**
     * 更新Order
     */
    void updateOrder(Order order);

    /**
     * 获取Order列表
     */
    List<Order>getOrderByParameter(Map map);
}
