package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 * Review实体
*/
@Data
public class Review {
    private Long reviewId;
    //商家
    private Store store;
    //'服务评分'
    private Double serviceScore;
    //分量评分
    private Double weightScore;
    //分量评分
    private Double tasteScore;
    //综合评分
    private Double avgScore;
    //评分内容  如果未null 标示没有评价内容
    private String scoreContent;
    //用户
    private Customer customer;
    //点单编号
    private Order order;
    //评价时间
    private Date reviewTime;
    //是否自动评价
    private Boolean isAutoReview;
    //是否删除
    private Boolean isDeleted;

}
