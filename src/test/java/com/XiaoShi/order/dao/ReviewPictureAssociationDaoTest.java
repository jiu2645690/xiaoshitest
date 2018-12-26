package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.ReviewPictureAssociationDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.dao.ReviewDao;
import com.xiaoshi.order.pojo.entity.ReviewPictureAssociation;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ReviewPictureAssociationDaoTest {

    @Autowired
    private ReviewPictureAssociationDao reviewPictureAssociationDao;
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private ReviewDao reviewDao;

    @Test
    public void select() {
        ReviewPictureAssociation reviewPictureAssociation = reviewPictureAssociationDao.select(1L);
        assertNotNull(reviewPictureAssociation);
    }

    @Test
    public void insert() {
        ReviewPictureAssociation reviewPictureAssociation = new ReviewPictureAssociation();
        reviewPictureAssociation.setPicture(pictureDao.select(1L));
        reviewPictureAssociation.setReview(reviewDao.select(1L));
        reviewPictureAssociationDao.insert(reviewPictureAssociation);
        TestCase.assertTrue(reviewPictureAssociationDao.select(reviewPictureAssociation.getReview().getReviewId()).getReview().getReviewId().equals(reviewPictureAssociation.getReview().getReviewId()));
    }

    @Test
    public void update() {
        ReviewPictureAssociation reviewPictureAssociation = reviewPictureAssociationDao.select(1L);
        reviewPictureAssociation.setPicture(pictureDao.select(2L));
        reviewPictureAssociationDao.updateReviewPictureAssociation(reviewPictureAssociation);
        TestCase.assertTrue(reviewPictureAssociationDao.select(1L).getPicture().getPictureId().equals(2L));
    }

}