package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Feedback;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface FeedbackDao {
    /**
     * 通过id获取Feedback
     */
    Feedback select(Long id);
    /**
     * 新增Feedback
     */
    void insert(Feedback feedback);
    /**
     * 获取Feedback列表
     */
    List<Feedback> selectList();
    /**
     *批量新增 Feedback
     */
    void insertBatch(List<Feedback> feedbacks);
    /**
     * 通过传入参数查询是否存在对应Feedback
     */
    List<Feedback> getFeedbackByParameter(Map map);
    /**
     * 更新Feedback
     */
    void updateFeedback(Feedback feedback);
}
