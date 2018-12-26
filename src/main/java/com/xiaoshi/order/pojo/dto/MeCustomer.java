package com.xiaoshi.order.pojo.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 获取我的基本信息
 */
@Data
public class MeCustomer {
    //昵称
    private String nickName;
    //名
    private String firstName;
    //姓
    private String lastName;
    //余额
    private BigDecimal balance;
    //性别
    private String sex;
}
