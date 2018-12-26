package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 * FoodItemAndTemplateAssociation实体
*/
@Data
public class FoodItemAndTemplateAssociation {
    //模板编号
    private FoodItemTemplate foodItemTemplate;
    //菜品编号
    private FoodItem foodItem;
}
