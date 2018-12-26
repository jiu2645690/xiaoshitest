package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.Review;
import com.xiaoshi.order.service.ReviewService;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.CustomerService;
import com.xiaoshi.order.service.ComboTemplateService;
import com.xiaoshi.order.service.StoreService;
import com.xiaoshi.order.service.PictureService;
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
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ComboTemplateService comboTemplateService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PictureService pictureService;

    @Test
    public void get() {
        assertNotNull(reviewService.get(1L));
    }

    @Test
    public void insertReview() {
        Review review = new Review();
        review.setReviewTime(new Date());
        review.setCustomer(customerService.get(1L));
        reviewService.insertReview(review);
        TestCase.assertTrue(reviewService.get(review.getReviewId()).getReviewId().equals(review.getReviewId()));
    }

    @Test
    public void updateReview() {
        Review review = reviewService.get(1L);
        review.setStore(storeService.get(1L));
        reviewService.updateReview(review);
        TestCase.assertTrue(reviewService.get(1L).getStore().getStoreId().equals(1L));
    }
}