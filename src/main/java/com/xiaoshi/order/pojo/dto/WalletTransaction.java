package com.xiaoshi.order.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 前台返回前台店特惠区
 */
@Data
public class WalletTransaction {
    // 流水类型：0 支付， 1提现 2充值 3退款
    private Long walleType;
    //金额
    private BigDecimal amount;
    //操作时间
    private Date createdAt;
}
