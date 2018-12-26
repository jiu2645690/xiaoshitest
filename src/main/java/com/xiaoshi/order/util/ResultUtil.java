package com.xiaoshi.order.util;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.model.ResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** Result工具类 */
public class ResultUtil {
  /** 成功返回数据并返回objectjson数据 */
  public static ResponseEntity success(Object object) {
    ResultModel resultModel = new ResultModel();
    resultModel.setCode(OrderCode.SUCCESS.getCode());
    resultModel.setMessage(OrderCode.SUCCESS.getMessage());
    resultModel.setData(object);
    return new ResponseEntity(resultModel, HttpStatus.OK);
  }
  /** 成功返回数据 */
  public static ResponseEntity success() {
    return success(null);
  }

  /** 异常返回数据OrderCode */
  public static ResponseEntity error(OrderCode orderCode) {
    ResultModel resultModel = new ResultModel();
    resultModel.setCode(orderCode.getCode());
    resultModel.setMessage(orderCode.getMessage());
    return new ResponseEntity(resultModel, HttpStatus.valueOf(orderCode.getCode()));
  }

  /** 异常返回数据OrderException */
  public static ResponseEntity error(OrderException ex) {
    ResultModel resultModel = new ResultModel();
    resultModel.setCode(ex.getCode());
    resultModel.setMessage(ex.getMessage());
    return new ResponseEntity(resultModel, HttpStatus.valueOf(ex.getCode()));
  }

  /** 异常返回数据code and message */
  public static ResponseEntity error(Integer code, String message) {
    ResultModel resultModel = new ResultModel();
    resultModel.setCode(code);
    resultModel.setMessage(message);
    return new ResponseEntity(resultModel, HttpStatus.valueOf(code));
  }
}
