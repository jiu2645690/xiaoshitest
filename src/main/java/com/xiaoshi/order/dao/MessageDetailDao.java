package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.MessageDetail;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface MessageDetailDao {
    /**
     * 通过id获取MessageDetail
     */
    MessageDetail select(Long id);
    /**
     * 新增MessageDetail
     */
    void insert(MessageDetail messageDetail);
    /**
     * 获取MessageDetail列表
     */
    List<MessageDetail> selectList();
    /**
     * 获取MessageDetail列表
     */
    List<MessageDetail> getMessageDetailByParameter(Map map);
    /**
     *批量新增 MessageDetail
     */
    void insertBatch(List<MessageDetail> messageDetails);
    /**
     * 通过传入参数查询是否存在对应MessageDetail
     */
    MessageDetail getStoreByParameter(Long id);
    /**
     * 更新MessageDetail
     */
    void updateMessageDetail(MessageDetail messageDetail);
}
