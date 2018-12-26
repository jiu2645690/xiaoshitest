package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.ShoppingCart;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShoppingCartDao {
    /**
     * 通过id获取 Picture
     */
    ShoppingCart select(Long id);
    /**
     * 新增 Picture
     */
    void insert(ShoppingCart shoppingCart);
    /**
     * 获取Picture列表
     */
    List<ShoppingCart> selectList();
    /**
     * 通过customerId获取 Picture
     */
    ShoppingCart  getShoppingCartByCustomerId(Long customerId);
}
