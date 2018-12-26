package com.XiaoShi.order.util;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.constant.UserTypeCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.pojo.entity.Store;
import com.xiaoshi.order.pojo.model.TokenModel;
import com.xiaoshi.order.util.JsonUtil;
import com.xiaoshi.order.util.RedisUtil;
import com.xiaoshi.order.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TokenUtilTest {

  @Rule public ExpectedException thrown = ExpectedException.none();

  /** 断言 一个 为null 的用户实例 */
  @Test
  public void setNullCustomerToken() {
    Customer nullCustomer = null;
    thrown.expect(OrderException.class);
    thrown.expectMessage(OrderCode.TOKEN_SET_FAIL.getMessage());
    TokenUtil.setToken(nullCustomer);
  }

  /** 断言 一个 仅实例化 的用户实例 */
  @Test
  public void setEmptyCustomerToken() {
    Customer emptyCustomer = new Customer();
    assertNotNull(TokenUtil.getTokenModel(TokenUtil.setToken(emptyCustomer)));
  }

  /** 断言 一个实例化 并带有数据 的用户实例 */
  @Test
  public void setCommonCustomerToken() {
    Customer commonCustomer = new Customer();
    commonCustomer.setCustomerId(1L);
    assertNotNull(TokenUtil.getTokenModel(TokenUtil.setToken(commonCustomer)));
  }

  /** 断言 一个 为null 的商家实例 */
  @Test
  public void setNullStoreToken() {
    Store nullStore = null;
    thrown.expect(OrderException.class);
    thrown.expectMessage(OrderCode.TOKEN_SET_FAIL.getMessage());
    TokenUtil.setToken(nullStore);
    ;
  }

  /** 断言 一个 仅实例化 的商家实例 */
  @Test
  public void setEmptyStoreToken() {
    Store emptyStore = new Store();
    assertNotNull(TokenUtil.getTokenModel(TokenUtil.setToken(emptyStore)));
  }

  /** 断言 一个实例化 并带有数据 的商家实例 */
  @Test
  public void setCommonStoreToken() {
    Store commonStore = new Store();
    commonStore.setStoreId(1L);
    assertNotNull(TokenUtil.getTokenModel(TokenUtil.setToken(commonStore)));
  }

  /** 断言 一个 获取不到 的tokenmodel */
  @Test
  public void getNullTokenModel() {
    thrown.expect(OrderException.class);
    thrown.expectMessage(OrderCode.TOKEN_GET_FAIL.getMessage());
    TokenUtil.getTokenModel("123");
  }

  /** 断言 一个UserTypeCode为CUSTOMER 但是获取不到对应 Customer */
  @Test
  public void getErrorOfCustomerTokenModel() {
    TokenModel tokenModel = new TokenModel();
    // 设置用户类型
    tokenModel.setUserType(UserTypeCode.CUSTOMER.getCode());
    // 将 tokenmodel 序列化成 josn 格式
    String tokenValue = JsonUtil.toJson(tokenModel);
    RedisUtil.set("57ac6663-798b-4485-ad51-faa8100bdaab", tokenValue);
    thrown.expect(OrderException.class);
    thrown.expectMessage(OrderCode.TOKEN_GET_ERROR.getMessage());
    TokenUtil.getTokenModel("57ac6663-798b-4485-ad51-faa8100bdaab");
  }

  /** 断言 一个UserTypeCode为Store 但是获取不到对应 Store */
  @Test
  public void getErrorofStoreTokenModel() {
    TokenModel tokenModel = new TokenModel();
    // 设置用户类型
    tokenModel.setUserType(UserTypeCode.STORE.getCode());
    // 将 tokenmodel 序列化成 josn 格式
    String tokenValue = JsonUtil.toJson(tokenModel);
    RedisUtil.set("9f796d8c-c077-4140-9fe0-07bb3257e94e", tokenValue);
    thrown.expect(OrderException.class);
    thrown.expectMessage(OrderCode.TOKEN_GET_ERROR.getMessage());
    TokenUtil.getTokenModel("9f796d8c-c077-4140-9fe0-07bb3257e94e");
  }

  /** 断言 一个UserTypeCode为Store 并能获取到数据 */
  @Test
  public void getCommonCustomerTokenModel() {
    Customer customer = new Customer();
    assertNotNull(TokenUtil.getTokenModel(TokenUtil.setToken(customer)).getCustomer());
  }

  /** 断言 一个UserTypeCode为Store 并能获取到数据 */
  @Test
  public void getCommonStoreTokenModel() {
    Store store = new Store();
    assertNotNull(TokenUtil.getTokenModel(TokenUtil.setToken(store)).getStore());
  }
}
