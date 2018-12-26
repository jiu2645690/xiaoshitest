package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 * ComboTemplate实体
*/
@Data
public class Combo {
    // 用户套餐主键
    private Long comboId;
    //设置 店铺中的套餐。一个店铺 可以 有多个套餐
    private ComboTemplate comboTemplate;

}
