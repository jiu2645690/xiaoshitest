package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 *  FeedbackPictureAssociation实体
*/
@Data
public class FeedbackPictureAssociation {
    //消息
    private Feedback feedback;
    // 图片
    private Picture picture;

}
