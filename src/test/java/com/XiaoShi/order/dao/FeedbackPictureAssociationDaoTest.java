package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.FeedbackDao;
import com.xiaoshi.order.dao.FeedbackPictureAssociationDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.pojo.entity.FeedbackPictureAssociation;
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
public class FeedbackPictureAssociationDaoTest {

    @Autowired
    private FeedbackPictureAssociationDao feedbackPictureAssociationDao;
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private FeedbackDao feedbackDao;

    @Test
    public void select() {
        FeedbackPictureAssociation feedbackPictureAssociation = feedbackPictureAssociationDao.select(1L);
        assertNotNull(feedbackPictureAssociation);
    }

    @Test
    public void insert() {
        FeedbackPictureAssociation feedbackPictureAssociation = new FeedbackPictureAssociation();
        feedbackPictureAssociation.setFeedback(feedbackDao.select(1L));
        feedbackPictureAssociation.setPicture(pictureDao.select(1L));
        feedbackPictureAssociationDao.insert(feedbackPictureAssociation);
        TestCase.assertTrue(feedbackPictureAssociationDao.select(1L).getFeedback().getFeedbackId().equals(1L));
    }
}