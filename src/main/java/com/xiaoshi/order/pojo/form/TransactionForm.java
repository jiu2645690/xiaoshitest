package com.xiaoshi.order.pojo.form;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *获取商店参数
 */
@Data
public class TransactionForm {
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
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Integer month;
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Integer year;
    private Long walleType;
}
