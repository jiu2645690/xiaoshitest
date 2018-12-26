package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.dao.FeedbackDao;
import com.xiaoshi.order.pojo.entity.Feedback;
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
public class FeedbackDaoTest {

    @Autowired
    private FeedbackDao feedbackDao;
    @Autowired
    private StoreDao storeDao;

    @Test
    public void select() {
        Feedback feedback = feedbackDao.select(1L);
        assertNotNull(feedback);
    }

    @Test
    public void insert() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackContent("wwwww");
        feedbackDao.insert(feedback);
        TestCase.assertTrue(feedbackDao.select(feedback.getFeedbackId()).getFeedbackId().equals(feedback.getFeedbackId()));
    }
}