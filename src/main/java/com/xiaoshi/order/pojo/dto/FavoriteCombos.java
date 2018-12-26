package com.xiaoshi.order.pojo.dto;

import lombok.Data;

/**
 * 获取收藏店铺列表
 */
@Data
public class FavoriteCombos {
    private Long comboTemplateId;
    //名称
    private String comboTemplateName;
    //封面图片路径
    private String pictureUrl;
}
