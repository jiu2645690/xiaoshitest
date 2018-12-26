package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Wallet实体
*/
@Data
public class Wallet {

    private Long walletId;
    //流水类型：0 支付， 1提现 2充值 3退款
    private Integer walleType;
    //用户类型（可以是 商家和用户）
    private Integer peopleType;
    //对应记录的 系统流水。交易有争论，我们可以追溯到 系统流水 获取转账方式和对应银行或者支付接口 流水单号
    private Transcation transcation;
    //用户编号（商家编号或者用户编号）
    private Long peopleId;
    //金额
    private BigDecimal amount;
    //操作时间
    private Date createdAt;
    //最后修改时间
    private Date lastUpdatedAt;
    //用途：0 支付， 1提现 2充值 3退款
    private Boolean isDeleted;
}
