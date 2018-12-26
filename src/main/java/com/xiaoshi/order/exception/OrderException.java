package com.xiaoshi.order.exception;

import com.xiaoshi.order.constant.OrderCode;
import lombok.Data;

/** 自定义异常类 */
@Data
public class OrderException extends RuntimeException {

  private Integer code;

  /** 异常 */
  public OrderException(OrderCode orderCode) {
    super(orderCode.getMessage());
    this.code = orderCode.getCode();
  }
}
