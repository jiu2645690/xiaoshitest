package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.dao.MessageDetailDao;
import com.xiaoshi.order.pojo.entity.MessageDetail;
import com.xiaoshi.order.pojo.entity.Wallet;
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
public class MessageDetailDaoTest {

    @Autowired
    private MessageDetailDao messageDetailDao;
    @Autowired
    private StoreDao storeDao;

    @Test
    public void select() {
        MessageDetail messageDetail = messageDetailDao.select(1L);
        assertNotNull(messageDetail);
    }

    @Test
    public void insert() {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setMessageContent("wwww");
        messageDetailDao.insert(messageDetail);
        TestCase.assertTrue(messageDetailDao.select(messageDetail.getMessageDetailId()).getMessageDetailId().equals(messageDetail.getMessageDetailId()));
    }
}