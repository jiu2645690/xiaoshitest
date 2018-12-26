package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.Feedback;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface FeedbackService {
    Feedback get(Long id);

    List<Feedback> getFeedbackList();

    PageModel search();

    void insertFeedback(Feedback feedback);

    List<Feedback>  getFeedbackByParameter(Map map);

    void updateFeedback(Feedback feedback);
}
