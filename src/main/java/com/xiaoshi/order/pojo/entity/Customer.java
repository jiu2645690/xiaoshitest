package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Customer实体
*/
@Data
public class Customer {
    //用户主键id
    private Long customerId;
    //电话号码
    private String phoneNumber;
    //邮箱
    private String email;
    //微信id
    private String wechatOpenid;
    //电话号码
    private String phoneNumberIsValidated;
    //邮箱
    private String emailIsValidated;
    //微信id
    private String wechatOpenidIsValidated;
    //头像对应图片id
    private Picture picture;
    //'创建时间'
    private Date createdAt;
    //昵称
    private String nickName;
    //名
    private String firstName;
    //姓
    private String lastName;
    //性别
    private boolean sex;
    //余额
    private BigDecimal balance;
    //取餐地址id
    private Address address;
}
