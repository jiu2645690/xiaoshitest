package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.MessageDetailDao;
import com.xiaoshi.order.pojo.entity.MessageDetail;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.MessageDetailService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *MessageDetailServiceImpl
*/
@Service
@Slf4j
public class MessageDetailServiceImpl implements MessageDetailService {

    @Autowired
    private MessageDetailDao MessageDetailDao;

    /**
     * 通过id获取 MessageDetail
     */
    @Override
    public MessageDetail get(Long id) {
        return MessageDetailDao.select(id);
    }

    /**
     * 获取 MessageDetail列表
     */
    @Override
    public List<MessageDetail> getMessageDetailList() {
        return MessageDetailDao.selectList();
    }

    /**
     *  分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<MessageDetail> list = MessageDetailDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 MessageDetail
     */
    @Override
    public void insertMessageDetail(MessageDetail MessageDetail) {
        MessageDetailDao.insert(MessageDetail);
    }

    /**
     * 通过Store获取MessageDetail
     */
    @Override
    public List<MessageDetail> getMessageDetailByParameter(Map map) {
        return MessageDetailDao.getMessageDetailByParameter(map);
    }
    /**
     * 更新MessageDetail
     */
    @Override
    public void updateMessageDetail(MessageDetail messageDetail) {
        MessageDetailDao.updateMessageDetail(messageDetail);
    }
}
