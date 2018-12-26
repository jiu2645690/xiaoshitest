package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 * OrderAndFoodItemComboAssociation实体
*/
@Data
public class OrderAndFoodItemComboAssociation {
    //订单
    private Order order;
    // 套餐
    private Combo combo;
    //菜品
    private FoodItem foodItem;
    // 菜品 数量
    private Integer foodItemCount;
    // 套餐数量
    private Integer comboTemplateCount;
    // 是否删除
    private Boolean isDeleted;
}
