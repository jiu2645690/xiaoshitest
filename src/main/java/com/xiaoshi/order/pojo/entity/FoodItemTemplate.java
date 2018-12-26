package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 * FoodItemTemplate实体
*/
@Data
public class FoodItemTemplate {
    private Long foodItemTemplateId;
    //菜品模板名称
    private String foodItemTemplateName;
    //创建时间
    private Date createdAt;
    //是否删除
    private Boolean isDeleted;
}
