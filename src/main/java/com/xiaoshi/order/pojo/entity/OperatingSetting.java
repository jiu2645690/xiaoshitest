package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 * OperatingSetting实体
*/
@Data
public class OperatingSetting {
    //商家
    private Store store;
    //营运日期
    private Date date;
    //菜品模板表主键id
    private FoodItemTemplate currentFoodItemTemplateId;

}
