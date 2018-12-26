package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 * Favorite实体
*/
@Data
public class Favorite {
    //收藏主键id
    private Long favoriteId;
    //用户
    private Customer customerId;
    //商家
    private Store storeId;
    //菜品主键id
    private FoodItem foodItemId;
    //套餐主键id
    private ComboTemplate comboTemplateId;


}
