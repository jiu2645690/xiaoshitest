package com.xiaoshi.order.pojo.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 补全订单信息验证下单
*/
@Data
public class OrderVerifyForm {
    private Long orderId;
    //送餐日期
    private Date deliveryDate;
    //0 午餐 1晚餐
    private Integer deliveryTime;
    //税收
    private BigDecimal taxes;
    //消费
    private BigDecimal tip;
    //备注
    private String remark;

}
