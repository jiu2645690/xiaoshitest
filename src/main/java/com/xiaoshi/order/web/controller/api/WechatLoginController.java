package com.xiaoshi.order.web.controller.api;


import com.xiaoshi.order.pojo.dto.UserLogin;
import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.pojo.entity.CustomerAuth;
import com.xiaoshi.order.service.CustomerAuthService;
import com.xiaoshi.order.service.CustomerService;
import com.xiaoshi.order.service.SendMailService;
import com.xiaoshi.order.util.ResultUtil;
import com.xiaoshi.order.service.ShoppingCartService;
import com.xiaoshi.order.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信Controller
 */

@RestController
@EnableTransactionManagement
@RequestMapping("/v1/wechat")
public class WechatLoginController {

    @Autowired
    private SendMailService sendMailService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerAuthService customerAuthService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    private static String VERRIFICATIONCODE = "VERRIFICATIONCODE";

    /**
     * 编号：2-1-007,微信登录
     */
    @PostMapping("/customerSignin")
    public ResponseEntity PhoneLogin(@RequestParam(value = "wechatOpenId", required = true) String wechatOpenId,
                                     @RequestParam(value = "password", required = true) String password) {
        customerService.isLoginCustomerExist("wechatOpenId", wechatOpenId);
        Customer customertemp = customerService.getCustomerByParameter("wechatOpenId", wechatOpenId);
        CustomerAuth customerAuth = customerAuthService.
                getCustomerByParameter(customertemp.getCustomerId());
        UserLogin userLogin = LoginUtil.toUserLogin(customerAuth, password, shoppingCartService.getShoppingCartByCustomerId(customerAuth.getCustomer().getCustomerId()));
        return ResultUtil.success(userLogin);
    }

}
