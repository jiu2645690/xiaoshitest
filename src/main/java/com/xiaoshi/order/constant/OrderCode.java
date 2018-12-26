package com.xiaoshi.order.constant;

/** 返回状态码枚举 */
public enum OrderCode {
  SUCCESS(200, "操作成功"),
  INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
  USER_ALREADY_EXISTS(409, "用户已存在"),
  USER_NOT_EXISTS(404, "用户不存在"),
  STORE_NOT_EXISTS(404, "店铺不存在"),
  INFO_NOT_EXISTS(404, "相关信息不存在"),
  MAIL_NOT_SEND(400, "邮件发送失败"),
  PARM_NOT_EMPTY(400, "传递参数不能存在空值"),
  PWD_NOT_EQUAL(400, "两次密码输入不一样"),
  PWD_NOT_SUCCESS(400, "密码加密失败"),
  PWD_IS_FAIL(400, "密码解密失败"),
  YZM_NOT_EQUAL(400, "验证码输入有误或已过期"),
  LOGIN_NOT_SUCCESS(400, "已注册"),
  PARAMS_ERROR(400, "参数错误"),
  LOGIN_ERROR(400, "登录失败"),
  MAIL_NOT_FORMAT(400, "邮件格式有误"),
  ADDRESS_CHANGE_FAIL(400,"变更地址失败，请清空购物车"),
  TOKEN_SET_FAIL(401, "空的用户信息"),
  TOKEN_GET_FAIL(401, "授权信息不存在"),
  MESSAGE_SEND_FAIL(403, "短信发送失败，请稍后再试"),
  TOKEN_GET_ERROR(401, "授权信息错误"),
  USERNAME_ALREADY_EXISTS(409, "用户名已存在"),
  FOOD_NOT_EXISTS(404, "菜品不存在"),
  ORDERSTATE_NOT_EXISTS(404, "状态为不可评价"),
  COMBOSUM_NOT_EXISTS(404, "剩余套餐数量不足"),
  COMBO_NOT_DATE(404, "该套餐已经过期"),
  COMBO_NOT_EXISTS(404, "套餐不存在"),
  FOODTEMP_NOT_EXISTS(404, "菜品模板不存在"),
  COMBOINFOOD_NOT_EXISTS(404, "套餐中午菜品不存在"),
  FOODSUM_NOT_EXISTS(404, "剩余菜品数量不足"),
  FOOD_NOT_DOWN(404, "菜品已下架"),
  UPLOAD_NOT_SUCCESS(400, "文件上传失败"),
  PHONE_NOT_FORMAT(400, "手机格式有误"),
  DATE_NOT_FORMAT(400, "时间格式有误");

  private Integer code;
  private String message;

  OrderCode(Integer code, String message) {
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
