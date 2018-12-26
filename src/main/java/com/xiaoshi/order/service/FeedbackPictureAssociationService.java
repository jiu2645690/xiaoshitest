package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.FeedbackPictureAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface FeedbackPictureAssociationService {
    FeedbackPictureAssociation get(Long id);

    List<FeedbackPictureAssociation> getFeedbackPictureAssociationList();

    PageModel search();

    void insertFeedbackPictureAssociation(FeedbackPictureAssociation feedbackPictureAssociation);

    List<FeedbackPictureAssociation> getFeedbackPictureAssociationByParameter(Map map);

    void updateFeedbackPictureAssociation(FeedbackPictureAssociation feedbackPictureAssociation);
}
