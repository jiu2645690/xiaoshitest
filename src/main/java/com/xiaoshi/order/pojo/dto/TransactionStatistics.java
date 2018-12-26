package com.xiaoshi.order.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 前台返回前台店特惠区
 */
@Data
public class TransactionStatistics {
    //营业额统计
    private BigDecimal turnoverStatistics;
    //到账金额统计
    private BigDecimal zhangPeriodStatistics;
    //提现金额统计
    private BigDecimal withdrawalStatistics;
    //充值金额统计
    private BigDecimal rechargeStatistics;
    //退款金额统计
    private BigDecimal refundStatistics;
}
