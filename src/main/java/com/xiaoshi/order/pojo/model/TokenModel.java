package com.xiaoshi.order.pojo.model;

import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.pojo.entity.Store;
import lombok.Data;

/**
 * token 实体
 **/
@Data
public class TokenModel{
    // 用户类型
    private Integer userType;
    //  顾客实体
    private Customer customer;
    // 后续增加 商家实体
    private Store store;
    // 员工和超级理员实体
}
