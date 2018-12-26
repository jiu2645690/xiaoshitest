package com.xiaoshi.order.pojo.form;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *获取商店参数
 */
@Data
public class ReviewForm {
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    private String token;
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Long orderId;
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Double tasteScore;
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Double serviceScore;
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Double weightScore;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    private String scoreContent;

}
