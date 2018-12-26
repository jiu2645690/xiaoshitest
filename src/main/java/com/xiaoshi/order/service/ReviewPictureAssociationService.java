package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.ReviewPictureAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface ReviewPictureAssociationService {
    ReviewPictureAssociation get(Long id);

    List<ReviewPictureAssociation> getReviewPictureAssociationList();

    PageModel search();

    void insertReviewPictureAssociation(ReviewPictureAssociation reviewPictureAssociation);

    void updateReviewPictureAssociation(ReviewPictureAssociation reviewPictureAssociation);

    List<ReviewPictureAssociation> getReviewPictureAssociationByParameter(Map map);
}
