package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 * Address实体
*/
@Data
public class Address {
    // 地址主键
    private Long addressId;
    // 地址
    private String addressName;
    //邮编
    private String zipCode;
    //城市
    private String city;
    //州
    private String state;
    //创建时间
    private Date createdAt;
    // 死否删除
    private Boolean isDeleted;
}
