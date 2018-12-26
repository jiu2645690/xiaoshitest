package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.ReviewPictureAssociation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ReviewPictureAssociationDao {
    /**
     * 通过id获取ReviewPictureAssociation
     */
    ReviewPictureAssociation select(Long id);
    /**
     * 新增ReviewPictureAssociation
     */
    void insert(ReviewPictureAssociation reviewPictureAssociation);
    /**
     * 获取ReviewPictureAssociation列表
     */
    List<ReviewPictureAssociation> selectList();
    /**
     *批量新增 ReviewPictureAssociation
     */
    void insertBatch(List<ReviewPictureAssociation> reviewPictureAssociations);
    /**
     * 更新ReviewPictureAssociation
     */
    void updateReviewPictureAssociation(ReviewPictureAssociation reviewPictureAssociation);
    /**
     * 通过参数获取ReviewPictureAssociation列表
     */
    List<ReviewPictureAssociation> getReviewPictureAssociationByParameter(Map map);
}
