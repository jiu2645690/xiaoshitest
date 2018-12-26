package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.Transcation;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface TranscationService {
    Transcation get(Long id);

    List<Transcation> getTranscationList();

    PageModel search();

    void insertTranscation(Transcation transcation);

    List<Transcation> getTranscationByParameter(Map map);

    void updateTranscation(Transcation transcation);
}
