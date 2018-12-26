package com.xiaoshi.order.pojo.dto;

import lombok.Data;

/**
 * 获取收藏店铺列表
 */
@Data
public class FavoriteFoodItems {
    private Long foodItemId;
    //名称
    private String name;
    //封面图片路径
    private String pictureUrl;
}
