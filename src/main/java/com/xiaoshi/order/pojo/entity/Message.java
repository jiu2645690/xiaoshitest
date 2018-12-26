package com.xiaoshi.order.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 *  Message实体
*/
@Data
public class Message {
    //消息id
    private Long messageId;
    //商家
    private Store store;
    // 用户
    private Customer customer;
    //创建时间
    private Date createdAt;
}
