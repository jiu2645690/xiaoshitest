package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 * SalesSetting实体
*/
@Data
public class SalesSetting {
    //商家
    private Store store;
    //'午餐无条件退款时间'
    private Date lunchRefundInterval;
    //'晚餐无条件退款时间'
    private Date dinnerRefundInterval;
    //'午餐截止下单时间'
    private Date lunchOrderendTime;
    //'晚餐截止下单时间'
    private Date dinnerOrderendTime;
    //'是否开放午餐
    private Boolean isHaveLunch;
    //'是否开放晚餐
    private Boolean isHaveDinner;
    //'封面图片id
    private Picture picture;

}
