package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 * FoodItemPictureAssociation实体
*/
@Data
public class FoodItemPictureAssociation {
    //模板编号
    private Picture picture;
    //图片id
    private FoodItem foodItem;
}
