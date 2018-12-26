package com.xiaoshi.order.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 获取所有订单分页排序
*/
@Data
public class OrderList {
    //店铺编号
    private String storeName;
    // 短内容
    private String shortContent;
    //总金额
    private BigDecimal totalAmount;
}
