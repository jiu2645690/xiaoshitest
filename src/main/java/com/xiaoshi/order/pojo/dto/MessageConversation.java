package com.xiaoshi.order.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户和商家对话列表
 */
@Data
public class MessageConversation {
    //商家昵称
    private String storeNickName;
    //商家id
    private Long storeId;
    //消息id
    private Long messageId;
}
