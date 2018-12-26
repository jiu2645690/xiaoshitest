package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Order实体
*/
@Data
public class Order {
    private Long orderId;
    //可展示订单编号
    private String uuId;
    //送餐地址编号
    private Address address;
    //店铺编号
    private Store store;
    //用户编号
    private Customer customer;
    //0 午餐 1晚餐
    private Integer deliveryTime;
     //选择的送餐日期
    private Date deliveryDate;
    //下单时间
    private Date createdAt;
    //最后修改时间
    private Date lastUpdatedAt;
    //送达时间
    private Date deliveredTime;
    //退单时间
    private Date refundTime;
    //总金额
    private BigDecimal totalAmount;
    //菜品金额总计
    private BigDecimal subTotal;
    //税收
    private BigDecimal taxes;
    //消费
    private BigDecimal tip;
    //备注
    private String remark;
    //退款原因
    private String refundReason;
    //状态  0-预订单（下单展示页） 1-进行中  2已送达  3已完成（或者带评价） 4已完成 5申请退款中 6 已退款
    private Integer state;
    //账期状态（0 为到账，1 已到账）
    private Integer zhangPeriod;
    //商家是否删除
    private Boolean storeIsDeleted;
    //用户是否删除
    private Boolean customerIsDeleted;
    //用户是否阅
    private Boolean customerIsRead;
    //商家是否阅读
    private Boolean storeIsRead;
    //商家收益=菜品金额*（1-当前的系统抽成比例）
    private BigDecimal storeEarnings;
    //平台收益=菜品金额总计*当前的系统抽成比例
    private BigDecimal platformEarnings;
    //系统抽成比例
    private BigDecimal proportional;

}
