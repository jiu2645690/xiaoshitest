package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 *  MessageDetail实体
*/
@Data
public class MessageDetail {
    // 消息详情id
    private Long messageDetailId;
    // 消息
    private Message message;
    // 发送人 id
    private Long sendPeopleId;
    // 发送人 类型
    private Integer sendPeopleType;
    // 消息发送人是否阅读
    private Boolean sendIsRead;
    // 接收人id
    private Long receivePeopleId;
    // 接收人类型
    private Integer receivePeopleType;
    // 接收人是否阅读
    private Boolean receiveIsRead;
    // 消息内容
    private String messageContent;
    // 发送时间
    private Date sendTime;
}
