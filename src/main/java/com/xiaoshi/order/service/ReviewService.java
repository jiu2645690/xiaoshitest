package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.Review;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface ReviewService {
    Review get(Long id);

    List<Review> getReviewList();

    PageModel search();

    void insertReview(Review review);

    void updateReview(Review review);

    List<Review> getReviewByParameter(Map map);
}
