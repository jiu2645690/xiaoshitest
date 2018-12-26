package com.xiaoshi.order.pojo.form;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *获取商店参数
 */
@Data
public class AddressAndStateForm {

    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Long addressId;
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private Integer state;
}
