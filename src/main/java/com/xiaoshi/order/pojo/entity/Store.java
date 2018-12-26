package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.math.BigDecimal;

/**
 *  Store实体
 */
@Data
public class Store {
    //用户主键id
    private Long storeId;
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
    //昵称
    private String nickName;
    //头像对应图片id
    private Picture picture;
    //名
    private String firstName;
    //姓
    private String lastName;
    //店铺地址 真实地址
    private String businessAddress;
    //简介
    private String introduction;
    //星级
    private Integer star;
    //综合评分
    private Double averageScore;
    //店铺名称
    private String storeName;
    //性别
    private boolean sex;
    //是否删除
    private boolean isDeleted;
    //余额
    private BigDecimal balance;

}
