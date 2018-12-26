package com.xiaoshi.order.pojo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 4-1-005，获取购物车列表
 */
@Data
public class FoodItemCart {
    private Long storeId;
    //送餐日期
    private Date deliveryDate;
    // 送餐时段
    private Integer deliveryTime;
    // 套餐或菜品
    private List<FoodItemOrCombo> foodItemOrComboList;
}
