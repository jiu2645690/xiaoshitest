package com.xiaoshi.order.pojo.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 3-0-007返回前台当前日期运营的菜品
 */
@Data
public class ReturnFoodItem {
    //菜品id
    private Long foodItemId;
    //菜品名称
    private String name;
    //价格
    private BigDecimal price;
    //封面图片路径
    private String pictureUrl;
    //库存
    private Integer remainingCount;
    // 特惠价格
    private BigDecimal dailySpecialPrice;
    // 是否收藏
    private Boolean isFavorite;
    //商品主材
    private String primaryIngredient;
    //商品辅材
    private String complementaryIngredient;
    //描述
    private String description;
}
