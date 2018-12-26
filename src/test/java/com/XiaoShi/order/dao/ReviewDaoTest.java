package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.CustomerDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.dao.ReviewDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.pojo.entity.Review;
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
public class ReviewDaoTest {

    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private PictureDao pictureDao;

    @Test
    public void select() {
        Review review = reviewDao.select(1L);
        assertNotNull(review);
    }

    @Test
    public void insert() {
        Review review = new Review();
        review.setReviewTime(new Date());
        review.setCustomer(customerDao.select(1L));
        reviewDao.insert(review);
        TestCase.assertTrue(reviewDao.select(review.getReviewId()).getReviewId().equals(review.getReviewId()));
    }

    @Test
    public void update() {
        Review review = reviewDao.select(1L);
        review.setStore(storeDao.select(1L));
        reviewDao.updateReview(review);
        TestCase.assertTrue(reviewDao.select(1L).getStore().equals(1L));
    }

}