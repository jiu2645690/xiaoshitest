package com.xiaoshi.order.pojo.form;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 *将套餐添加到购物车参数
 */
@Data
public class FoodItemForm {
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    private String token;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    private Date deliveryDate;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    private Integer deliveryTime;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    private Integer count;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    private Long storeId;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    private Long foodItemId;
}
