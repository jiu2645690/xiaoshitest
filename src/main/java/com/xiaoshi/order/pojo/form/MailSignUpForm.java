package com.xiaoshi.order.pojo.form;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 邮箱注册参数
 */
@Data
public class MailSignUpForm {
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    private String nickName;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    private String verifyCode;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    private String password;
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空")
    @NotBlank(message = "不能为空")
    private String verifyPassword;
}
