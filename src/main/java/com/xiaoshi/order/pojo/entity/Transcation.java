package com.xiaoshi.order.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Transcation实体
*/
@Data
public class Transcation {
    private Long transcationId;
    //订单编号
    private Order order;
    //流水号（可能是微信的流水号，支付宝或者银联）
    private Long transcationNumber;
    //金额
    private BigDecimal amount;
    //创建时间
    private Date createdAt;
    //支付方式： 微信， 支付包，银联
    private Integer paymentMethod;
    //用途：0 支付， 1提现 2充值 3退款
    private Integer type;
    //customerid
    private Customer customer;
    //store
    private Store store;
    //是否成功
    private Boolean isSucess;
    //失败原因
    private String failReson;
    //是否删除
    private Boolean isDeleted;

}
