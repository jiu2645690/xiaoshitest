package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 *  CustomerAuth实体
*/
@Data
public class CustomerAuth {
    //'用户账户表id'
    private Long customerAuthId;
    //'用户
    private Customer customer;
    //'用户秘钥'
    private String customerSecretkey;
    //'密码'
    private String password;
    //'允许移动端登陆'
    private String allowLogin;
    //'注册时间'
    private Date createdAt;

}
