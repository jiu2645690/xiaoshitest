package com.xiaoshi.order.service.imp;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.service.SendMailService;
import com.xiaoshi.order.util.RandomSumUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

/**
 * SendMailServiceImpl
 */
@Service
@Slf4j
@ConfigurationProperties(prefix = "mail")
@Data
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    public static String SUBJECT = "邮箱验证码";
    private String username;

    /**
     * 发送邮件
     */
    @Override
    public String sendEmail(String to) {
        try {
            MimeMessage parentMimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(parentMimeMessage, true, "utf-8");
            mimeMessageHelper.setSubject(SUBJECT);
            mimeMessageHelper.setFrom(new InternetAddress(username));
            mimeMessageHelper.setSentDate(new Date());
            // 设置收件人地址
            mimeMessageHelper.setTo(to);
            // 设置正文
            String zym = RandomSumUtil.getRandonString(4);
            mimeMessageHelper.setText(zym, true);
            // 正式发送邮件
            javaMailSender.send(parentMimeMessage);
            return zym;
        } catch (Exception e) {
            throw new OrderException(OrderCode.MAIL_NOT_SEND);
        }
    }

}
