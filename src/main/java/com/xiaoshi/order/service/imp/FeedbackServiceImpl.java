package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.FeedbackDao;
import com.xiaoshi.order.pojo.entity.Feedback;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.FeedbackService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *FeedbackServiceImpl
*/
@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    /**
     * 通过id获取 Feedback
     */
    @Override
    public Feedback get(Long id) {
        return feedbackDao.select(id);
    }

    /**
     * 获取 Feedback列表
     */
    @Override
    public List<Feedback> getFeedbackList() {
        return feedbackDao.selectList();
    }

    /**
     *  分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<Feedback> list = feedbackDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 Feedback
     */
    @Override
    public void insertFeedback(Feedback feedback) {
        feedbackDao.insert(feedback);
    }

    /**
     * 通过Store获取Feedback
     */
    @Override
    public List<Feedback> getFeedbackByParameter(Map map) {
        return feedbackDao.getFeedbackByParameter(map);
    }
    /**
     * 更新Feedback
     */
    @Override
    public void updateFeedback(Feedback Feedback) {
        feedbackDao.updateFeedback(Feedback);
    }
}
