package com.xiaoshi.order.pojo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 前台返回前台店铺评价
 */
@Data
public class StoreReview {
    //用户头像地址
    private String customerAvatarUrl;
    //用户名
    private String customerNickName;
    //评价时间
    private Date reviewTime;
    //评价内容
    private String scoreContent;
    //评价图片列表
    private List pictureList;
}
