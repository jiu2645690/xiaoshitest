package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.FeedbackPictureAssociation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface FeedbackPictureAssociationDao {
    /**
     * 通过id获取FeedbackPictureAssociation
     */
    FeedbackPictureAssociation select(Long id);
    /**
     * 新增FeedbackPictureAssociation
     */
    void insert(FeedbackPictureAssociation feedbackPictureAssociation);
    /**
     * 获取FeedbackPictureAssociation列表
     */
    List<FeedbackPictureAssociation> selectList();
    /**
     *批量新增 FeedbackPictureAssociation
     */
    void insertBatch(List<FeedbackPictureAssociation> feedbackPictureAssociations);
    /**
     * 通过传入参数查询是否存在对应FeedbackPictureAssociation
     */
    List<FeedbackPictureAssociation> getFeedbackPictureAssociationByParameter(Map map);
    /**
     * 更新FeedbackPictureAssociation
     */
    void updateFeedbackPictureAssociation(FeedbackPictureAssociation feedbackPictureAssociation);
}
