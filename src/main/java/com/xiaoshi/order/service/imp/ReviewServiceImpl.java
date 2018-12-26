package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.ReviewDao;
import com.xiaoshi.order.pojo.entity.Review;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.ReviewService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *  ReviewServiceImpl
 **/
@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    /**
     * 通过id获取Review
     */
    @Override
    public Review get(Long id) {
        return reviewDao.select(id);
    }

    /**
     * 获取Review列表
     */
    @Override
    public List<Review> getReviewList() {
        return reviewDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<Review> list = reviewDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 Review
     */
    @Override
    public void insertReview(Review review) {
        reviewDao.insert(review);
    }

    /**
     * 更新Review
     */
    @Override
    public void updateReview(Review review){
        reviewDao.updateReview(review);
    }

    /**
     * 通过参数获取Review列表
     */
    @Override
    public List<Review> getReviewByParameter(Map map) {
        return reviewDao.getReviewByParameter(map);
    }
}
