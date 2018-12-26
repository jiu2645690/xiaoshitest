package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.dao.FeedbackDao;
import com.xiaoshi.order.dao.MessageDao;
import com.xiaoshi.order.dao.MessageDetailDao;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.MessageConversation;
import com.xiaoshi.order.pojo.entity.*;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.MessageService;
import com.xiaoshi.order.util.PageUtil;
import com.xiaoshi.order.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *MessageServiceImpl
*/
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private FeedbackDao feedbackDao;
    @Autowired
    private MessageDetailDao messageDetailDao;

    /**
     * 通过id获取 Message
     */
    @Override
    public Message get(Long id) {
        return messageDao.select(id);
    }

    /**
     * 获取 Message列表
     */
    @Override
    public List<Message> getMessageList() {
        return messageDao.selectList();
    }

    /**
     * 信息中心统计
     */
    @Override
    public Integer getMyMessageAmount(String token) {
        Integer amount = 0;
        Customer customer= TokenUtil.getTokenModel(token).getCustomer();
        Store store= TokenUtil.getTokenModel(token).getStore();
        if(store == null && customer ==null )
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        if(customer!=null){
            map.put("customerId",customer.getCustomerId());
        }
        if(store!=null){
            map.put("storeId",store.getStoreId());
        }
        List<Message> messages = messageDao.getMessageByParameter(map);
        if (messages == null){
            amount = amount;
        }else{
            if (messages.size() == 0) amount = amount;
            if (messages.size() > 0) amount = amount+messages.size();
        }
        //1顾客2商家 发送人
        Map feedbackMap=new HashMap();
        if(customer!=null){
            feedbackMap.put("sendPeopleType",1);
            feedbackMap.put("sendPeopleId",customer.getCustomerId());
        }
        if(store!=null){
            feedbackMap.put("sendPeopleType",2);
            feedbackMap.put("sendPeopleId",store.getStoreId());
        }
        List<Feedback> feedbacks = feedbackDao.getFeedbackByParameter(feedbackMap);
        if (feedbacks == null){
            amount = amount;
        }else{
            if (feedbacks.size() == 0) amount = amount;
            if (feedbacks.size() > 0) amount = amount+feedbacks.size();
        }
        //1顾客2商家 接收人
        Map feedbackTempMap=new HashMap();
        if(customer!=null){
            feedbackTempMap.put("receivePeopleType",1);
            feedbackTempMap.put("receivePeopleId",customer.getCustomerId());
        }
        if(store!=null){
            feedbackTempMap.put("receivePeopleType",2);
            feedbackTempMap.put("receivePeopleId",store.getStoreId());
        }
        List<Feedback> feedbackTemps = feedbackDao.getFeedbackByParameter(feedbackTempMap);
        if (feedbackTemps == null){
            amount = amount;
        }else{
            if (feedbackTemps.size() == 0) amount = amount;
            if (feedbackTemps.size() > 0) amount = amount+feedbackTemps.size();
        }
        return amount;
    }

    /**
     * 统计反馈信息
     */
    @Override
    public Integer getMessageFeedbackAmount(String token) {
        Integer amount = 0;
        Map feedbackTempMap=new HashMap();
        Customer customer= TokenUtil.getTokenModel(token).getCustomer();
        Store store= TokenUtil.getTokenModel(token).getStore();
        if(customer!=null){
            //1顾客2商家 接收人
            feedbackTempMap.put("receivePeopleType",1);
            feedbackTempMap.put("receivePeopleId",customer.getCustomerId());
        }
        if(store!=null){
            //1顾客2商家 接收人
            feedbackTempMap.put("receivePeopleType",2);
            feedbackTempMap.put("receivePeopleId",store.getStoreId());
        }
        if(store == null && customer ==null )
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        List<Feedback> feedbackTemps = feedbackDao.getFeedbackByParameter(feedbackTempMap);
        if (feedbackTemps == null){
            amount = amount;
        }else{
            if (feedbackTemps.size() == 0) amount = amount;
            if (feedbackTemps.size() > 0) amount = amount+feedbackTemps.size();
        }
        return amount;
    }

    /**
     * 统计反馈信息
     */
    @Override
    public List<String> backMessageFeedback(String token) {
        List<String> lists= new ArrayList<>();
        Customer customer= TokenUtil.getTokenModel(token).getCustomer();
        Store store= TokenUtil.getTokenModel(token).getStore();
        Map feedbackTempMap=new HashMap();
        if(customer!=null){
            //1顾客2商家 接收人
            feedbackTempMap.put("receivePeopleType",1);
            feedbackTempMap.put("receivePeopleId",customer.getCustomerId());
        }
        if(store != null){
            //1顾客2商家 接收人
            feedbackTempMap.put("receivePeopleType",2);
            feedbackTempMap.put("receivePeopleId",store.getStoreId());
        }
        if(store == null && customer ==null )
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        List<Feedback> feedbackTemps = feedbackDao.getFeedbackByParameter(feedbackTempMap);
        if (feedbackTemps == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        for(Feedback feedback:feedbackTemps){
            lists.add(feedback.getFeedbackContent());
        }
        return lists;
    }

    /**
     * 用户和商家对话列表
     */
    @Override
    public List<MessageConversation> messageConversation(String token) {
        Integer amount = 0;
        List<MessageConversation> messageConversations=new ArrayList<MessageConversation>();
        Customer customer= TokenUtil.getTokenModel(token).getCustomer();
        if(customer==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        map.put("customerId",customer.getCustomerId());
        List<Message> messages = messageDao.getMessageByParameter(map);
        for(Message message:messages){
            MessageConversation messageConversation=new MessageConversation();
            messageConversation.setStoreId(message.getStore().getStoreId());
            messageConversation.setStoreNickName(message.getStore().getNickName());
            messageConversation.setMessageId(message.getMessageId());
            messageConversations.add(messageConversation);
        }
        return messageConversations;
    }

    /**
     *  分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<Message> list = messageDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 Message
     */
    @Override
    public void insertMessage(Message message) {
        messageDao.insert(message);
    }

    /**
     * 通过Store获取Message
     */
    @Override
    public List<Message> getMessageByParameter(Map map) {
        return messageDao.getMessageByParameter(map);
    }

    /**
     * 更新Message
     */
    @Override
    public void updateMessage(Message Message) {
        messageDao.updateMessage(Message);
    }

}
