package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 * ShoppingCart实体
*/
@Data
public class ShoppingCart {
    // 购物车 id
    private Long shoppingCartId;
    // 用户
    private Customer customer;

}
