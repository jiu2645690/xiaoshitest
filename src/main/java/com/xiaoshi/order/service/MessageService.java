package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.dto.MessageConversation;
import com.xiaoshi.order.pojo.entity.Message;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface MessageService {
    Message get(Long id);

    List<Message> getMessageList();

    Integer getMyMessageAmount(String token);

    Integer getMessageFeedbackAmount(String token);

    List<String> backMessageFeedback(String token);

    List<MessageConversation> messageConversation(String token);

    PageModel search();

    void insertMessage(Message message);

    List<Message> getMessageByParameter(Map map);

    void updateMessage(Message message);
}
