package com.xiaoshi.order.pojo.dto;

import lombok.Data;

/**
 * 前台用户登录返回参数
 */
@Data
public class UserLogin {
    //主键id
    private Long customerId;
    //用于商家登录主键id
    private Long storeId;
    //昵称
    private String nickName;
    //手机号
    private String phoneNumer;
    //邮箱
    private String email;
    //用户秘钥
    private String customerSecretkey;
    //商家秘钥
    private String storeSecretkey;
    //取餐地址id
    private Long addressId;
    //取餐地址
    private String addressName;
    //用户头像url
    private String avatarPictureUrl;
    //购物车id
    private Long shoppingCartId;
    // 用户token
    private String token;
}
