package com.xiaoshi.order.pojo.dto;

import lombok.Data;

/**
 * 获取收藏店铺列表
 */
@Data
public class FavoriteStores {
    private Long storeId;
    //名称
    private String storeName;
    //封面图片路径
    private String pictureUrl;
}
