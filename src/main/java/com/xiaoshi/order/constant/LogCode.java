package com.xiaoshi.order.constant;

/** 错误及操作日志-枚举 */
public enum LogCode {
  SEVER_ERROR(1, "系统错误"),
  OPERATING_ERROR(2, "操作日志");
  private Integer code;
  private String message;

  LogCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
  /** 获取 Code */
  public Integer getCode() {
    return code;
  }

  /** 获取 message */
  public String getMessage() {
    return message;
  }
}
