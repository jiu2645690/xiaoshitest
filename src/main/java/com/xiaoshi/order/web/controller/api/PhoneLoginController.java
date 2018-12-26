package com.xiaoshi.order.web.controller.api;

import com.xiaoshi.order.pojo.dto.UserLogin;
import com.xiaoshi.order.pojo.entity.*;
import com.xiaoshi.order.service.*;
import com.xiaoshi.order.util.LoginUtil;
import com.xiaoshi.order.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** 手机Controller */
@RestController
@RequestMapping("/v1/phone")
public class PhoneLoginController {

  @Autowired private SendMailService sendMailService;
  @Autowired private CustomerService customerService;
  @Autowired private StoreService storeService;
  @Autowired private CustomerAuthService customerAuthService;
  @Autowired private StorePictureAssociationService storePictureAssociationService;
  @Autowired private StoreAuthService storeAuthService;
  @Autowired private ShoppingCartService shoppingCartService;

  private static String VERRIFICATIONCODE = "VERRIFICATIONCODE";

  @PostMapping("/customerSignin")
  /** 编号：2-1-005,手机号登录 */
  public ResponseEntity PhoneLogin(
      @RequestParam(value = "phoneNumber", required = true) String phoneNumber,
      @RequestParam(value = "password", required = true) String password) {

    customerService.isLoginCustomerExist("phoneNumber", phoneNumber);
    Customer customertemp = customerService.getCustomerByParameter("phoneNumber", phoneNumber);
    CustomerAuth customerAuth =
        customerAuthService.getCustomerByParameter(customertemp.getCustomerId());

    UserLogin userLogin =
        LoginUtil.toUserLogin(
            customerAuth,
            password,
            shoppingCartService.getShoppingCartByCustomerId(
                customerAuth.getCustomer().getCustomerId()));
    return ResultUtil.success(userLogin);
  }

  @GetMapping("/customerVerify")
  /** 编号：2-1-009,验证手机号是否存在 */
  public ResponseEntity phoneVerify(
      @RequestParam(value = "phoneNumber", required = true) String phoneNumber) {
    return ResultUtil.success(customerService.isCustomerVerifyExist("phoneNumber", phoneNumber));
  }

  @GetMapping("/storeVerify")
  /** 编号：2-2-018,验证Store手机号是否存在 */
  public ResponseEntity phoneStoreVerify(
      @RequestParam(value = "phoneNumber", required = true) String phoneNumber) {
    return ResultUtil.success(storeService.isStoreVerifyExist("phoneNumber", phoneNumber));
  }

  @GetMapping("/storeSignin")
  /** 编号：2-2-014,手机号商家登录 */
  public ResponseEntity PhoneStoreLogin(
      @RequestParam(value = "phoneNumber", required = true) String phoneNumber,
      @RequestParam(value = "password", required = true) String password) {

    storeService.isLoginStoreExist("phoneNumber", phoneNumber);
    Store storetemp = storeService.getStoreByParameter("phoneNumber", phoneNumber);
    StoreAuth storeAuth = storeAuthService.getStoreByParameter(storetemp.getStoreId());

    UserLogin userLogin = LoginUtil.toStoreLogin(storeAuth, password);
    return ResultUtil.success(userLogin);
  }
}
