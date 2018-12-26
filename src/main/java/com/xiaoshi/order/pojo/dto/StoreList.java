package com.xiaoshi.order.pojo.dto;

import lombok.Data;
import java.util.List;

/**
 * 前台返回前台店铺列表
 */
@Data
public class StoreList {
    //用于商家登录主键id
    private Long storeId;
    //店铺名
    private String storeName;
    //是否收藏
    private Boolean isFavorite;
    //评分
    private Double avgScore;
    //评价数量
    private Long reviewCount;
    //送餐时间段
    private List mealTimeList;
    //封面图片地址
    private String pictureUrl;
    //介绍
    private String introduction;
    //星级
    private Integer star;
}