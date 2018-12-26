package com.xiaoshi.order.web.controller.api;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.constant.UserTypeCode;
import com.xiaoshi.order.dao.ShoppingCartLineItemDao;
import com.xiaoshi.order.pojo.dto.ReturnAddress;
import com.xiaoshi.order.pojo.entity.Address;
import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.pojo.model.TokenModel;
import com.xiaoshi.order.service.AddressService;
import com.xiaoshi.order.service.CustomerService;
import com.xiaoshi.order.service.ShoppingCartLineItemService;
import com.xiaoshi.order.util.ResultUtil;
import com.xiaoshi.order.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

/**
 *  获取地址Controller
*/
@EnableTransactionManagement
@RestController
@RequestMapping("/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private ShoppingCartLineItemDao shoppingCartLineItemDao;
    @Autowired
    private CustomerService customerService;
    /**
     *  编号：1-0-001，获取(系统设置的)送餐地址
     */
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ResultUtil.success(addressService.getReturnAddress(id));
    }

    /**
     * 编号：1-0-002，获取送餐地址列表
     */
    @GetMapping("")
    public ResponseEntity search() {
        return ResultUtil.success(addressService.getReturnAddressList());
    }

    @PutMapping("/customerBind")
    /**
     * 编号：1-1-003  用户绑定地址/或者用户切换地址
     */
    public ResponseEntity customerBind(@RequestHeader(value = "token", required = true) String token,
                                       @RequestParam(value = "addressId", required = true) Long addressId) {
        // 第一步验证token
        TokenModel tokenModel= TokenUtil.getTokenModel(token);
        if (tokenModel.getUserType()!= UserTypeCode.CUSTOMER.getCode())
            return  ResultUtil.error(OrderCode.TOKEN_GET_FAIL);
        //第二部验证 地址编号是否存在
        Address address= addressService.get(addressId);
        if (address==null)
            return  ResultUtil.error(OrderCode.INFO_NOT_EXISTS);
        // 第三部 验证 用户购物车中是否还有商品 否则不能切换
        int shoppingCartLineItemCount= shoppingCartLineItemDao.getShoppingCartLineItemCountByCustomerId(tokenModel.getCustomer().getCustomerId());
        if(shoppingCartLineItemCount>0)
            return  ResultUtil.error(OrderCode.ADDRESS_CHANGE_FAIL);
        //第四部绑定 地址
        Customer customer=new Customer();
        customer.setCustomerId(tokenModel.getCustomer().getCustomerId());
        customer.setAddress(address);
        customerService.updateCustomer(customer);
        return  ResultUtil.success();
    }

}
