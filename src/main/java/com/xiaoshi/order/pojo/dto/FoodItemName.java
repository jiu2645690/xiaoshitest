package com.xiaoshi.order.pojo.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 5-2-015商家 点击日历中某一天 查看今天需要备的菜
 */
@Data
public class FoodItemName {
    //菜品id
    private Long foodItemId;
    //菜品名称
    private String name;
}
