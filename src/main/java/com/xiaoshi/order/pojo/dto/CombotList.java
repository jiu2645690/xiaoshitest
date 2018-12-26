package com.xiaoshi.order.pojo.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 5-1-003，获取套餐列表
 */
@Data
public class CombotList {
    //套餐编号
    private Long comboId;
    //套餐数量
    private Integer comboCount;
    //套餐名称
    private String comboName;
    //套餐封面图片
    private String comboPictureUrl;
    //菜品编号
    private Long foodItemId;
    //菜品数量
    private Integer foodItemCount;
    //菜品名称
    private String foodItemName;
    //菜品价格
    private BigDecimal foodItemprice;
    //菜品封面图片
    private String foodItemPictureUrl;
}
