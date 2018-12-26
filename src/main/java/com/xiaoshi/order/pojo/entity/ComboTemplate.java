package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 * ComboTemplate实体
*/
@Data
public class ComboTemplate {
    private Long comboTemplateId;
    //套餐名称
    private String comboTemplateName;
    //包含菜品分数
    private Integer comboTemplateNumber;
    //折扣比例
    private Double comboTemplateDiscount;
    //可出售份数
    private Integer comboTemplateRemainingCount;
    //店铺主键id
    private Store store;
    //套餐图片id
    private Picture picture;
}
