package com.xiaoshi.order.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.xiaoshi.order.config.MessageConfig;
import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Json工具类 */
@Component
public class MessageUtil {
  @Autowired private MessageConfig messageConfig;
  @Autowired private static AWSCredentials awsCredentials;
  @Autowired private static AWSCredentialsProvider provider;
  @Autowired private static AmazonSNS amazonSNS;
  @Autowired private static Map<String, MessageAttributeValue> smsAttributes;
  @Autowired private MessageUtil messageUtil;
  private static MessageUtil staticmessageUtil;

  @PostConstruct
  public void init() {
    staticmessageUtil = messageUtil;
    // 初始化短信 配置参数
    if (smsAttributes == null) {
      smsAttributes = new HashMap<>();
      smsAttributes.put(
          "AWS.SNS.SMS.SenderID",
          new MessageAttributeValue()
              .withStringValue(messageConfig.getSenderID())
              .withDataType("String"));
      smsAttributes.put(
          "AWS.SNS.SMS.MaxPrice",
          new MessageAttributeValue()
              .withStringValue(messageConfig.getMaxPrice())
              .withDataType("Number"));
      smsAttributes.put(
          "AWS.SNS.SMS.SMSType",
          new MessageAttributeValue()
              .withStringValue(messageConfig.getSmsType())
              .withDataType("String"));
    }

    // 初始化账号相关参数
    awsCredentials =
        new AWSCredentials() {
          @Override
          public String getAWSAccessKeyId() {
            return messageConfig.getAwsAccessKeyId();
          }

          @Override
          public String getAWSSecretKey() {
            return messageConfig.getAwsSecretKey(); // 带有发短信权限的 IAM 的 SECRET_KEY
          }
        };

    provider =
        new AWSCredentialsProvider() {
          @Override
          public AWSCredentials getCredentials() {
            return awsCredentials;
          }

          @Override
          public void refresh() {}
        };

    amazonSNS =
        AmazonSNSClientBuilder.standard().withCredentials(provider).withRegion("us-east-1").build();
  }

  public void setamazonSNS(AmazonSNS mockAmazonSNS) {
    amazonSNS = mockAmazonSNS;
  }

  /** 发送短信 验证码 */
  public static String sendMessageCode(String phoneNumber) {
    return staticmessageUtil.bulitMessageCode(phoneNumber);
  }

  // 生成验证码 并发送
  public String bulitMessageCode(String phoneNumber) {
    // 验证是否发送短信
    if (messageConfig.getIsSend()) {
      // 生成手机验证码
      String code = RandomSumUtil.getRandonString(4);
      // 通过 MessageCodeTemplate和 ReplaceCode 来定义消息内容
      if (sendMessage(
          phoneNumber,
          messageConfig.getMessageCodeTemplate().replace(messageConfig.getReplaceCode(), code))) {
        // 这里后续加上 记录 该手机号 发送短信次数
        return code;
      } else {
        throw new OrderException(OrderCode.MESSAGE_SEND_FAIL);
      }
    }
    return messageConfig.getReplaceCode();
  }

  public boolean sendMessage(String phoneNumber, String message) {
    PublishResult publishResult = staticmessageUtil.sendSMSMessage(phoneNumber, message);
    if (publishResult.getSdkHttpMetadata().getHttpStatusCode() == 200) {
      return true;
    } else {
      return false;
    }
  }

  public PublishResult sendSMSMessage(String phoneNumber, String message) {
    return sendSMSMessage(phoneNumber, message, smsAttributes);
  }

  /** 根据d设置的相关参数发送短信。 */
  public PublishResult sendSMSMessage(
      String phoneNumber, String message, Map<String, MessageAttributeValue> smsAttributes) {
    return amazonSNS.publish(
        new PublishRequest()
            .withMessage(message)
            .withPhoneNumber(phoneNumber)
            .withMessageAttributes(smsAttributes));
  }
}
