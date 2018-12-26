package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 *  ReviewPictureAssociation实体
*/
@Data
public class ReviewPictureAssociation {
    // 评价
    private Review review;
    // 图片
    private Picture picture;
    // 删除
    private Boolean isDeleted;
}
