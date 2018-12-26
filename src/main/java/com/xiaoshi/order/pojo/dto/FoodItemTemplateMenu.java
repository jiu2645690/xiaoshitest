package com.xiaoshi.order.pojo.dto;

import com.xiaoshi.order.pojo.entity.FoodItemTemplate;
import com.xiaoshi.order.pojo.entity.Store;
import lombok.Data;
import java.util.Date;

/**
 * 前台获取菜品模板
*/
@Data
public class FoodItemTemplateMenu {
    //菜品模板表主键id
    private Long currentFoodItemTemplateId;
    //营运日期
    private Date date;
}
