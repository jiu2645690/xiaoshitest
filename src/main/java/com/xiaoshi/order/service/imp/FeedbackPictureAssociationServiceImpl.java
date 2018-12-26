package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.FeedbackPictureAssociationDao;
import com.xiaoshi.order.pojo.entity.FeedbackPictureAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.FeedbackPictureAssociationService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *FeedbackPictureAssociationServiceImpl
*/
@Service
@Slf4j
public class FeedbackPictureAssociationServiceImpl implements FeedbackPictureAssociationService {

    @Autowired
    private FeedbackPictureAssociationDao feedbackPictureAssociationDao;

    /**
     * 通过id获取 FeedbackPictureAssociation
     */
    @Override
    public FeedbackPictureAssociation get(Long id) {
        return feedbackPictureAssociationDao.select(id);
    }

    /**
     * 获取 FeedbackPictureAssociation列表
     */
    @Override
    public List<FeedbackPictureAssociation> getFeedbackPictureAssociationList() {
        return feedbackPictureAssociationDao.selectList();
    }

    /**
     *  分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<FeedbackPictureAssociation> list = feedbackPictureAssociationDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 FeedbackPictureAssociation
     */
    @Override
    public void insertFeedbackPictureAssociation(FeedbackPictureAssociation feedbackPictureAssociation) {
        feedbackPictureAssociationDao.insert(feedbackPictureAssociation);
    }

    /**
     * 通过Store获取FeedbackPictureAssociation
     */
    @Override
    public List<FeedbackPictureAssociation> getFeedbackPictureAssociationByParameter(Map map) {
        return feedbackPictureAssociationDao.getFeedbackPictureAssociationByParameter(map);
    }
    /**
     * 更新FeedbackPictureAssociation
     */
    @Override
    public void updateFeedbackPictureAssociation(FeedbackPictureAssociation feedbackPictureAssociation) {
        feedbackPictureAssociationDao.updateFeedbackPictureAssociation(feedbackPictureAssociation);
    }
}
