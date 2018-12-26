package com.xiaoshi.order.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * ShoppingCartLineItem实体
*/
@Data
public class ShoppingCartLineItem {
    // 购物车行 id
    private Long shoppingCartLineItemId;
    // 购物车
    private ShoppingCart shoppingCart;
    // 商品所在店铺
    private Store store;
    // 套餐
    private Combo combo;
    // 套餐数量
    private Long comboCount;
    // 菜品
    private FoodItem foodItem;

    private Date deliveryDate;
    private Date createdAt;
    private Date updatedAt;
    private Integer deliveryTime;
    private Integer state;
    private Integer foodItemCount;
}
