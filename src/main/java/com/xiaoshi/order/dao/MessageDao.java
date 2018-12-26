package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Message;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface MessageDao {
    /**
     * 通过id获取Message
     */
    Message select(Long id);
    /**
     * 新增Message
     */
    void insert(Message message);
    /**
     * 获取Message列表
     */
    List<Message> selectList();
    /**
     *批量新增 Message
     */
    void insertBatch(List<Message> messages);
    /**
     * 通过传入参数查询是否存在对应Message
     */
    Message getMessageByParameter(Long id);
    /**
     * 更新Message
     */
    void updateMessage(Message message);
    /**
     * 获取Message通过参数
     */
    List<Message> getMessageByParameter(Map map);
}
