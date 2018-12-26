package com.xiaoshi.order.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * 用户和商家对话列表
 */
@Data
public class MessageInfo {
    // 消息内容
    private String messageContent;
    //发送人
    private String sendPeople;
    //发送时间
    private Date sendTime;
}
