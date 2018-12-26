package com.xiaoshi.order.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * FoodItem实体
*/
@Data
public class FoodItem {
    private Long foodItemId;
    //菜品名称
    private String name;
    //价格
    private BigDecimal price;
    //库存数量
    private Integer remainingCount;
    //商品主材
    private String primaryIngredient;
    //商品辅材
    private String complementaryIngredient;
    //商品排序
    private Integer rank;
    //描述
    private String description;
    //特惠价格
    private BigDecimal dailySpecialPrice;
    //上下架
    private Boolean isAvailable;
    //店铺主键id
    private Store store;
    //图片主键id
    private Picture picture;
}
