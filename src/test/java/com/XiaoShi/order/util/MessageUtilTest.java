package com.XiaoShi.order.util;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.xiaoshi.order.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MessageUtilTest {

  @Test
  public void sendSMSMessage() {
    MessageUtil messageUtil = new MessageUtil();
    AmazonSNS awsCredentials = Mockito.mock(AmazonSNS.class);
    messageUtil.setamazonSNS(awsCredentials);
    PublishResult publicResult = Mockito.mock(PublishResult.class);
    Mockito.when(awsCredentials.publish(any())).thenReturn(publicResult);
    assertEquals(messageUtil.sendSMSMessage("13982590520", "测试"), publicResult);
  }
}
