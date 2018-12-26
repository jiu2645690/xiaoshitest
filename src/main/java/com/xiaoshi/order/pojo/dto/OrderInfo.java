package com.xiaoshi.order.pojo.dto;

import lombok.Data;
import java.util.Date;

/**
 * 详情 -获取 基本信息  （如果 是已完成订单，点击后变更 读取状态）
*/
@Data
public class OrderInfo {
    //送餐地址编号
    private String addressName;
    //店铺编号
    private String sotreName;
    //0 午餐 1晚餐
    private Integer deliveryTime;
    //选择的送餐日期
    private Date deliveryDate;
    //总金额
    private String totalAmount;
    //税收
    private String taxes;
    //消费
    private String tip;
    //备注
    private String remark;
    //退款原因
    private String refundReason;
}
