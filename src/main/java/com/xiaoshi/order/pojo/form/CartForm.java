package com.xiaoshi.order.pojo.form;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *购物车变更数量
 */
@Data
public class CartForm {
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    private String token;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    private Long shoppingCartLineItemId;
    private Integer comboCount;
    private Integer foodItemCount;
}
