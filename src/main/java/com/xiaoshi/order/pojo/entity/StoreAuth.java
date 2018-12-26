package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 * StoreAuth实体
*/
@Data
public class StoreAuth {
    //用户账户表id
    private Long storeAuthId;
    //用户
    private Store store;
    //用户秘钥
    private String storeSecretkey;
    //密码
    private String password;
    //允许移动端登陆
    private String allowLogin;
    //'允许后台登陆
    private String allowBackgroundLogin;
    //注册时间'
    private Date createdAt;

}
