package com.xiaoshi.order.pojo.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * FoodItemOrCombo
 */
@Data
public class FoodItemOrCombo {
    private Long shoppingCartLineItemId;
    //套餐编号
    private Long comboId;
    //菜品编号
    private Long foodItemId;
    //套餐数量
    private Integer comboCount;
    //套餐名称
    private String comboName;
    //封面图片路径
    private String comboPictureUrl;
    //菜品名称
    private String foodItemName;
    //菜品数量
    private Integer foodItemCount;
    // 菜品价格
    private BigDecimal foodItemprice;
    //菜品封面图片
    private String foodItemPictureUrl;
}
