package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Review;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ReviewDao {
    /**
     * 通过id获取Review
     */
    Review select(Long id);
    /**
     * 新增Review
     */
    void insert(Review review);
    /**
     * 获取Review列表
     */
    List<Review> selectList();
    /**
     *批量新增 Review
     */
    void insertBatch(List<Review> reviews);
    /**
     * 更新Review
     */
    void updateReview(Review review);
    /**
     * 通过id获取Review
     */
    List<Review> getReviewByParameter(Map map);
}
