package com.xiaoshi.order.pojo.form;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *获取商家所有订单分页排序
 */
@Data
public class OrderStoreForm {
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    private String token;
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Integer pageNum;
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Integer pageSize;
    private Long addressId;
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Integer orderState;
}
