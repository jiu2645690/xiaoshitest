package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 *  Feedback实体
*/
@Data
public class Feedback {

    private Long feedbackId;
    //发送人id
    private Long sendPeopleId;
    //发送人类型
    private Integer sendPeopleType;
    //发送人是否阅读
    private Boolean sendIsRead;
    //接收人 id
    private Long receivePeopleId;
    // 接收人 类型
    private Integer receivePeopleType;
    //接收人是否阅读
    private Boolean receiveIsRead;
    //被举报商家编号：来标示是否是举报信息 和被举报人的主键id，
    private Long beReportedStoreId;
    //消息内容
    private String feedbackContent;
}
