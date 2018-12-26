package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 * ComboTemplate实体
*/
@Data
public class ComboAndfFoodItemAssociation {
    // 套餐
    private Combo combo;
    // 菜品
    private FoodItem foodItem;
    // 菜品数量
    private Integer foodItemCount;
    // 是否删除
    private Boolean isDeleted;
}
