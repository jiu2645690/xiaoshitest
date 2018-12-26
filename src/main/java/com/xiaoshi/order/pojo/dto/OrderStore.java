package com.xiaoshi.order.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 5-2-011，获取商家所有订单分页排序
 */
@Data
public class OrderStore {
    private BigDecimal totalAmount;
    //送餐日期
    private Date deliveryDate;
    // 送餐时段
    private Integer deliveryTime;
    // 备注
    private String remark;
    // 订单编号
    private String uuId;
}
