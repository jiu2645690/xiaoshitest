package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.FoodItem;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodItemDao {
    /**
     * 通过id获取FoodItem
     */
    FoodItem select(Long id);
    /**
     * 新增FoodItem
     */
    void insert(FoodItem foodItem);
    /**
     * 获取FoodItem列表
     */
    List<FoodItem> selectList();
    /**
     *批量新增 FoodItem
     */
    void insertBatch(List<FoodItem> foodItems);
    /**
     * 更新CustomerAuth
     */
    void updateFoodItem(FoodItem foodItem);
}
