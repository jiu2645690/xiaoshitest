package com.xiaoshi.order.web.aop;

import com.google.common.base.Joiner;
import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.util.ResultUtil;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/** 用于获取返回前台定义的校验参数出错信息 */
@Component
@Aspect
public class GlobalAOP {

  // 配置要获取的校验参数出错信息的controller
  @Around("execution(* com.liwei.order.web.controller..*.*(..))")
  /** 全局注释参数校验 */
  public Object validateParams(ProceedingJoinPoint pig) throws Throwable {
    Object[] params = pig.getArgs();
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    for (Object param : params) {
      String httpMethod =
          ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
              .getRequest()
              .getMethod();
      if (httpMethod.equalsIgnoreCase("POST") || httpMethod.equalsIgnoreCase("PUT")) {
        if (param == null) {
          continue;
        }
        Set<ConstraintViolation<Object>> violations = validator.validate(param);
        if (violations != null && violations.size() > 0) {
          List<String> messages =
              violations.stream().map(o -> o.getMessage()).collect(Collectors.toList());
          return ResultUtil.error(OrderCode.PARAMS_ERROR.getCode(), Joiner.on(", ").join(messages));
        }
      }
    }
    return pig.proceed();
  }
}
