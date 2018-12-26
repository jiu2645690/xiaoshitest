package com.xiaoshi.order.web.controller.api;

import com.xiaoshi.order.pojo.dto.UserLogin;
import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.pojo.entity.CustomerAuth;
import com.xiaoshi.order.pojo.entity.Store;
import com.xiaoshi.order.pojo.entity.StoreAuth;
import com.xiaoshi.order.pojo.form.MailSignUpForm;
import com.xiaoshi.order.pojo.model.TokenModel;
import com.xiaoshi.order.service.*;
import com.xiaoshi.order.util.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** 发送邮件Controller */
@RestController
@RequestMapping("/v1/email")
public class MailLoginController {

  @Autowired private SendMailService sendMailService;
  @Autowired private CustomerService customerService;
  @Autowired private StoreService storeService;
  @Autowired private StoreAuthService storeAuthService;
  @Autowired private CustomerAuthService customerAuthService;
  @Autowired private ShoppingCartService shoppingCartService;

  private static String VERRIFICATIONCODE = "VERRIFICATIONCODE";

  /** 编号：2-1-003，发送邮箱验证码 */
  @GetMapping("/send/customerCode")
  public ResponseEntity sendMail(@RequestParam(value = "email", required = true) String email) {
    // 删除存在的验证码
    RedisUtil.delete(VERRIFICATIONCODE + email);
    // 验证邮箱格式
    ParameterCalibrationUtil.IsMail(email);
    // 发送邮件并返回验证码
    String yzm = sendMailService.sendEmail(email);
    // 验证码设置到内存
    RedisUtil.setSignUp(VERRIFICATIONCODE + email, yzm);
    return ResultUtil.success();
  }

  @PostMapping("/customerSignup")
  /** 编号：2-1-004，邮箱注册 */
  public ResponseEntity signUp(MailSignUpForm mailSignUpForm) {

    // 验证密码两次输入是否一样
    ParameterCalibrationUtil.IsEqual(
        mailSignUpForm.getPassword(), mailSignUpForm.getVerifyPassword());
    // 验证内存中是否存在该验证码
    ParameterCalibrationUtil.IsEqualVerificationCode(
        VERRIFICATIONCODE + mailSignUpForm.getEmail(), mailSignUpForm.getVerifyCode());
    // nickName是否已经注册
    customerService.isCustomerExist("nickName", mailSignUpForm.getNickName());
    // 邮箱是否已经注册
    customerService.isCustomerExist("email", mailSignUpForm.getEmail());
    // 生成加密的密码
    String decryptPassword = EncryptDecryptByKeyUtil.encrypt(mailSignUpForm.getPassword());
    // 插入customer
    Customer customer = new Customer();
    customer.setNickName(mailSignUpForm.getNickName());
    customer.setEmail(mailSignUpForm.getEmail());
    customer.setEmailIsValidated("1");
    customerService.insertCustomer(customer);
    // 插入CustomerAuth
    CustomerAuth customerAuth = new CustomerAuth();
    customerAuth.setPassword(EncryptDecryptByKeyUtil.md5(decryptPassword.toString().split(",")[1]));
    customerAuth.setCreatedAt(new Date());
    customerAuth.setCustomer(customer);
    customerAuth.setAllowLogin("1");
    customerAuth.setCustomerSecretkey(decryptPassword.toString().split(",")[0]);
    customerAuthService.insertCustomerAuth(customerAuth);
    // 删除内存中的验证码
    RedisUtil.delete(VERRIFICATIONCODE + mailSignUpForm.getEmail());
    return ResultUtil.success();
  }

  @GetMapping("/customerVerify")
  /** 编号：2-1-008,验证邮箱是否存在 */
  public ResponseEntity mailVerify(@RequestParam(value = "email", required = true) String email) {
    return ResultUtil.success(customerService.isCustomerVerifyExist("email", email));
  }

  @GetMapping("/storeVerify")
  /** 编号：2-1-008,验证store邮箱是否存在 */
  public ResponseEntity mailStoreVerify(
      @RequestParam(value = "email", required = true) String email) {
    return ResultUtil.success(storeService.isStoreVerifyExist("email", email));
  }

  @PostMapping("/customerSignin")
  /** 编号：2-1-006,邮箱登录 */
  public ResponseEntity mailLogin(
      @RequestParam(value = "email", required = true) String email,
      @RequestParam(value = "password", required = true) String password) {
    // 验证用户邮箱是否存在
    customerService.isLoginCustomerExist("email", email);
    Customer customertemp = customerService.getCustomerByParameter("email", email);
    CustomerAuth customerAuth =
        customerAuthService.getCustomerByParameter(customertemp.getCustomerId());
    // 获取返回数据
    UserLogin userLogin =
        LoginUtil.toUserLogin(
            customerAuth,
            password,
            shoppingCartService.getShoppingCartByCustomerId(
                customerAuth.getCustomer().getCustomerId()));
    return ResultUtil.success(userLogin);
  }

  @GetMapping("/storeSignin")
  /** 编号：2-1-015,邮箱商家登录 */
  public ResponseEntity mailStoreLogin(
      @RequestParam(value = "email", required = true) String email,
      @RequestParam(value = "password", required = true) String password) {
    // 验证用户邮箱是否存在
    storeService.isLoginStoreExist("email", email);
    Store storetemp = storeService.getStoreByParameter("email", email);
    StoreAuth storeAuth = storeAuthService.getStoreByParameter(storetemp.getStoreId());
    // 获取返回数据
    UserLogin userLogin = LoginUtil.toStoreLogin(storeAuth, password);
    return ResultUtil.success(userLogin);
  }

  @PostMapping("/code/customerVerify")
  /** 编号：2-1-011,邮箱验证码校验 */
  public ResponseEntity emailVerify(
      @RequestParam(value = "email", required = true) String email,
      @RequestParam(value = "verifyCode", required = true) String verifyCode) {
    // 验证redis中是否存在改校验码
    ParameterCalibrationUtil.IsEqualVerificationCode(VERRIFICATIONCODE + email, verifyCode);
    // 获取验证秘钥
    Customer customertemp = customerService.getCustomerByParameter("email", email);
    String passwordResetKey = TokenUtil.setToken(customertemp);
    return ResultUtil.success("passwordResetKey:" + passwordResetKey);
  }

  @PutMapping("/password/customerReset")
  /** 编号：2-1-012,重设密码 */
  public ResponseEntity customerPasswordReset(
      @RequestParam(value = "password", required = true) String password,
      @RequestParam(value = "resetPasswordKey", required = true) String resetPasswordKey,
      @RequestParam(value = "verifyPassword", required = true) String verifyPassword) {
    // 验证密码是否输入一样
    ParameterCalibrationUtil.IsEqual(password, verifyPassword);
    // 获取redis中校验码
    TokenModel tokenModel = TokenUtil.getTokenModel(resetPasswordKey);
    CustomerAuth customerAuth =
        customerAuthService.getCustomerByParameter(tokenModel.getCustomer().getCustomerId());
    String resetPassword =
        EncryptDecryptByKeyUtil.encrypt(password, customerAuth.getCustomerSecretkey());
    // 重设密码
    customerAuth.setPassword(EncryptDecryptByKeyUtil.md5(resetPassword));
    customerAuthService.updateCustomerAuth(customerAuth);
    return ResultUtil.success();
  }

  @PutMapping("/password/loginCustomerReset")
  /** 编号：2-1-013,重设密码,修改密码（登陆状态） */
  public ResponseEntity customerReset(
      @RequestParam(value = "token", required = true) String token,
      @RequestParam(value = "password", required = true) String password,
      @RequestParam(value = "verifyPassword", required = true) String verifyPassword) {
    // 验证密码是否输入一样
    ParameterCalibrationUtil.IsEqual(password, verifyPassword);
    // 获取redis中校验码
    TokenModel tokenModel = TokenUtil.getTokenModel(token);
    Customer customer = tokenModel.getCustomer();
    // 验证密码是否正确
    LoginUtil.isPassword(
        customerAuthService.getCustomerByParameter(customer.getCustomerId()), password);

    return ResultUtil.success("token:" + TokenUtil.setToken(tokenModel.getCustomer()));
  }

  @PostMapping("code/storeVerify")
  /** 编号：2-2-020,store邮箱验证码校验 */
  public ResponseEntity emailStoreVerify(
      @RequestParam(value = "email", required = true) String email,
      @RequestParam(value = "verifyCode", required = true) String verifyCode) {
    // 验证redis中是否存在改校验码
    ParameterCalibrationUtil.IsEqualVerificationCode(VERRIFICATIONCODE + email, verifyCode);
    // 获取验证秘钥
    Store storetemp = storeService.getStoreByParameter("email", email);
    String passwordResetKey = TokenUtil.setToken(storetemp);
    return ResultUtil.success("passwordResetKey:" + passwordResetKey);
  }

  @PutMapping("/password/storeReset")
  /** 编号：2-2-021,重设密码 */
  public ResponseEntity storePasswordReset(
      @RequestParam(value = "password", required = true) String password,
      @RequestParam(value = "resetPasswordKey", required = true) String resetPasswordKey,
      @RequestParam(value = "verifyPassword", required = true) String verifyPassword) {
    // 验证密码是否输入一样
    ParameterCalibrationUtil.IsEqual(password, verifyPassword);
    // 获取redis中校验码
    TokenModel tokenModel = TokenUtil.getTokenModel(resetPasswordKey);
    StoreAuth storeAuth = storeAuthService.getStoreByParameter(tokenModel.getStore().getStoreId());
    String resetPassword = EncryptDecryptByKeyUtil.encrypt(password, storeAuth.getStoreSecretkey());
    // 重设密码
    storeAuth.setPassword(EncryptDecryptByKeyUtil.md5(resetPassword));
    storeAuthService.updateStoreAuth(storeAuth);
    return ResultUtil.success();
  }

  @PutMapping("/password/loginStoreReset")
  /** 编号：2-2-022,重设密码,修改密码（登陆状态） */
  public ResponseEntity storeReset(
      @RequestParam(value = "token", required = true) String token,
      @RequestParam(value = "password", required = true) String password,
      @RequestParam(value = "verifyPassword", required = true) String verifyPassword) {
    // 验证密码是否输入一样
    ParameterCalibrationUtil.IsEqual(password, verifyPassword);
    // 获取redis中校验码
    TokenModel tokenModel = TokenUtil.getTokenModel(token);
    Store store = tokenModel.getStore();
    // 获取StoreAuth
    StoreAuth storeAuth = storeAuthService.getStoreByParameter(store.getStoreId());
    // 判断store密码是否正确
    LoginUtil.isStorePassword(storeAuth, password);
    return ResultUtil.success("token:" + TokenUtil.setToken(store));
  }
}
