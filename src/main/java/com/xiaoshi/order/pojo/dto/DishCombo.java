package com.xiaoshi.order.pojo.dto;

import lombok.Data;

/**
 * 前台返回前台店特惠区
 */
@Data
public class DishCombo {
    //套餐Id
    private Long comboId;
    //套餐名称
    private String name;
    //封面图片路径
    private String pictureUrl;
    //库存
    private Double comboTemplateDiscount;
    //套餐名称
    private Integer remainingCount;
    // 是否收藏
    private Boolean isFavorite;
}
