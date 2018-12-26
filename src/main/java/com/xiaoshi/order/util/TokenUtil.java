package com.xiaoshi.order.util;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshi.order.constant.LogCode;
import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.constant.UserTypeCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.pojo.entity.Log;
import com.xiaoshi.order.pojo.entity.Store;
import com.xiaoshi.order.pojo.model.TokenModel;

/** token工具类 */
public class TokenUtil {
  /** 生成顾客token */
  public static String setToken(Customer customer) {

    if (customer == null) {
      throw new OrderException(OrderCode.TOKEN_SET_FAIL);
    }
    try {
      // 创建 uuid
      String tokenKey = java.util.UUID.randomUUID().toString();
      // 实例化实体
      TokenModel tokenModel = new TokenModel();
      // 设置用户类型
      tokenModel.setUserType(UserTypeCode.CUSTOMER.getCode());
      // 设置 顾客 信息
      tokenModel.setCustomer(customer);
      // 将 tokenmodel 序列化成 josn 格式
      String tokenValue = JsonUtil.toJson(tokenModel);
      // 将 token 放到键值数据库
      RedisUtil.set(tokenKey, tokenValue);
      return tokenKey;
    } catch (Exception e) {
      Log log = new Log();
      log.setLogContent("Title:token 读取错误 /r/n Content:" + e.getMessage());
      log.setLogType(LogCode.SEVER_ERROR.getCode());
      log.setPeopleType(UserTypeCode.CUSTOMER.getCode());
      log.setPeopleId(customer.getCustomerId());
      // 添加错误日志
      LogUtil.setErrorLog(log);
      throw new OrderException(OrderCode.INTERNAL_SERVER_ERROR);
    }
  }
  /** 生成商家token */
  public static String setToken(Store store) {

    if (store == null) {
      throw new OrderException(OrderCode.TOKEN_SET_FAIL);
    }
    try {
      // 创建 uuid
      String tokenKey = java.util.UUID.randomUUID().toString();
      // 实例化实体
      TokenModel tokenModel = new TokenModel();
      // 设置用户类型
      tokenModel.setUserType(UserTypeCode.STORE.getCode());
      // 设置 顾客 信息
      tokenModel.setStore(store);
      // 将 tokenmodel 序列化成 josn 格式
      String tokenValue = JsonUtil.toJson(tokenModel);
      // 将 token 放到键值数据库
      RedisUtil.set(tokenKey, tokenValue);
      return tokenKey;
    } catch (Exception e) {
      Log log = new Log();
      log.setLogContent("Title:token 读取错误 /r/n Content:" + e.getMessage());
      log.setLogType(LogCode.SEVER_ERROR.getCode());
      log.setPeopleType(UserTypeCode.STORE.getCode());
      log.setPeopleId(store.getStoreId());
      // 添加错误日志
      LogUtil.setErrorLog(log);
      throw new OrderException(OrderCode.INTERNAL_SERVER_ERROR);
    }
  }
  /** 根据 tokenkey 返回 相关 tokenmodel */
  public static TokenModel getTokenModel(String token) {
    try {
      String tokenValue = RedisUtil.get(token);
      // 判断 获取 tokenvlue 值是否为空
      if (tokenValue == null || tokenValue.isEmpty())
        //  如果为空 提示授权信息不存在
        throw new OrderException(OrderCode.TOKEN_GET_FAIL);
      TokenModel tokenModel = (TokenModel) JSONObject.parseObject(tokenValue, TokenModel.class);

      // 判断用户类型
      if (tokenModel.getUserType() == UserTypeCode.CUSTOMER.getCode())
        // 判断对应的用户类型
        if (tokenModel.getCustomer() == null) throw new OrderException(OrderCode.TOKEN_GET_ERROR);
      // 商家用户类型
      if (tokenModel.getUserType() == UserTypeCode.STORE.getCode())
        // 判断对应的商家类型
        if (tokenModel.getStore() == null) throw new OrderException(OrderCode.TOKEN_GET_ERROR);
      return tokenModel;
    } catch (OrderException e) {
      // 添加错误日志
      Log log = new Log();
      log.setLogContent("Title:token 读取错误 /r/n Content:" + e.getMessage());
      log.setLogType(LogCode.SEVER_ERROR.getCode());
      LogUtil.setErrorLog(log);
      throw e;
    }
  }
}
