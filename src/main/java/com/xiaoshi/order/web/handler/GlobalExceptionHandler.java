package com.xiaoshi.order.web.handler;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/** 全局返回未定义的异常数据为500, "服务器内部错误" */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  /** @Description:返回实体 */
  public ResponseEntity handle(Exception e) {
    if (e instanceof OrderException) {
      OrderException ex = (OrderException) e;
      return ResultUtil.error(ex);
    } else {
      return ResultUtil.error(OrderCode.INTERNAL_SERVER_ERROR);
    }
  }
}
