package com.XiaoShi.order.service;

import com.xiaoshi.order.service.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SendMailServiceTest {

    @Autowired
    private SendMailService sendMailService;

    @Test
    public void get() {
        sendMailService.sendEmail("772855657@163.com");
    }


}