package com.xiaoshi.order.constant;

/**
 *  用户类型-返回状态码枚举
*/
public enum UserTypeCode {
    CUSTOMER(1, "顾客"),
    STORE(2, "商家"),
    SYSTEM(3, "系统管理员"),
    ADMIN(4, "超级管理员");
    private Integer code;
    private String message;
    UserTypeCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    /**
     * 获取 Code
     */
    public Integer getCode() {
        return code;
    }
    /**
     * 获取 message
     */
    public String getMessage() {
        return message;
    }
}
