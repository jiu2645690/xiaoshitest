package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.MessageDetail;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface MessageDetailService {
    MessageDetail get(Long id);

    List<MessageDetail> getMessageDetailList();

    PageModel search();

    void insertMessageDetail(MessageDetail messageDetail);

    List<MessageDetail> getMessageDetailByParameter(Map map);

    void updateMessageDetail(MessageDetail messageDetail);
}
