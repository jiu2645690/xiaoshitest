package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.dao.MessageDao;
import com.xiaoshi.order.pojo.entity.Message;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MessageDaoTest {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private StoreDao storeDao;

    @Test
    public void select() {
        Message message = messageDao.select(1L);
        assertNotNull(message);
    }

    @Test
    public void insert() {
        Message message = new Message();
        message.setCreatedAt(new Date());
        message.setStore(storeDao.select(1L));
        messageDao.insert(message);
        TestCase.assertTrue(messageDao.select(message.getMessageId()).getMessageId().equals(message.getMessageId()));
    }
}