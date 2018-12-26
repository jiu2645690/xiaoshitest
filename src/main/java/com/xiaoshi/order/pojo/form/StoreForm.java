package com.xiaoshi.order.pojo.form;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *获取商店参数
 */
@Data
public class StoreForm {
    private String token;
    private Integer pageNum;
    private Integer pageSize;
    private Integer star;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Long addressId;
    private Double averageScore;
    private Double searchContent;
    private Integer deliveryTime;
}
