package com.xiaoshi.order.pojo.dto;

import com.xiaoshi.order.pojo.entity.FoodItem;
import lombok.Data;

/**
 * 菜品及对应菜品count
 */
@Data
public class FoodItemCount {
    //菜品id
    private FoodItem foodItem;
    //对应菜品数量
    private Integer count;
}
