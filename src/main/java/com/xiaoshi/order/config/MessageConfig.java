package com.xiaoshi.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** 短信配置 */
@Component
@ConfigurationProperties(prefix = "message")
@Data
public class MessageConfig {
  // 带有发短信权限的 IAM 的 ACCESS_KEY_ID
  private String awsAccessKeyId;
  // 带有发短信权限的 IAM 的 SECRET_KEY
  private String awsSecretKey;
  // 消息发送人id
  private String senderID;
  // 允许每条信息 消耗的最大金额
  private String maxPrice;
  // 消息类型分 交易和促销
  private String smsType;
  // 是否发送消息
  private Boolean isSend;
  // 验证码消息模板
  private String messageCodeTemplate;
  // 验证码中要替换的值
  private String replaceCode;
}
