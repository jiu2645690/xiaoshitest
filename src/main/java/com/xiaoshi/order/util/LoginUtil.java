package com.xiaoshi.order.util;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.UserLogin;
import com.xiaoshi.order.pojo.entity.*;
import org.springframework.beans.BeanUtils;

/**
 *  用验证前台Store、customer登录的密码和返回用户登录后的数据
*/
public class LoginUtil {

    /**
     * 判断密码是否正确
     */
    public static void isPassword(CustomerAuth customerAuth, String password) {
        if (customerAuth.getPassword().toString().equals(EncryptDecryptByKeyUtil.
                md5(EncryptDecryptByKeyUtil.encrypt(password, customerAuth.getCustomerSecretkey())))) {
        } else {
            throw new OrderException(OrderCode.LOGIN_ERROR);
        }
    }


    /**
    * 返回 登录前台UserLogin
    */
    public static UserLogin toUserLogin(CustomerAuth customerAuth, String password, ShoppingCart shoppingCart) {
        if (customerAuth.getPassword().toString().equals(EncryptDecryptByKeyUtil.
                md5(EncryptDecryptByKeyUtil.encrypt(password, customerAuth.getCustomerSecretkey())))) {
            Customer customer = customerAuth.getCustomer();
            UserLogin userLogin = new UserLogin();
            BeanUtils.copyProperties(customer, userLogin);
            if(customer.getAddress() != null){
                BeanUtils.copyProperties(customer.getAddress(), userLogin);
                userLogin.setAddressId(customer.getAddress().getAddressId());
            }
            if(customer.getPhoneNumber()!= null){
                userLogin.setPhoneNumer(customer.getPhoneNumber());
            }
            if(customer.getPicture() != null){
                userLogin.setAvatarPictureUrl(customer.getPicture().getPictureUrl());
            }
            userLogin.setCustomerSecretkey(customerAuth.getCustomerSecretkey());
            if(shoppingCart != null){
                userLogin.setShoppingCartId(shoppingCart.getShoppingCartId());
            }
            userLogin.setToken(TokenUtil.setToken(customer));
            return userLogin;
        } else {
            throw new OrderException(OrderCode.LOGIN_ERROR);
        }
    }

    /**
     *  返回 登录前台StoreLogin
     */
    public static UserLogin toStoreLogin(StoreAuth storeAuth, String password) {
        if (storeAuth.getPassword().toString().equals(EncryptDecryptByKeyUtil.
                md5(EncryptDecryptByKeyUtil.encrypt(password, storeAuth.getStoreSecretkey())))) {
            Store store = storeAuth.getStore();
            UserLogin userLogin = new UserLogin();
            BeanUtils.copyProperties(store, userLogin);
            if(store.getPhoneNumber()!= null){
                userLogin.setPhoneNumer(store.getPhoneNumber());
            }
            if(store.getPicture() != null){
                userLogin.setAvatarPictureUrl(store.getPicture().getPictureUrl());
            }
            userLogin.setStoreSecretkey(storeAuth.getStoreSecretkey());
            userLogin.setToken(TokenUtil.setToken(store));
            return userLogin;
        } else {
            throw new OrderException(OrderCode.LOGIN_ERROR);
        }
    }

    /**
     *  返回 登录前台StoreLogin
     */
    public static void isStorePassword(StoreAuth storeAuth, String password) {
        if (storeAuth.getPassword().toString().equals(EncryptDecryptByKeyUtil.
                md5(EncryptDecryptByKeyUtil.encrypt(password, storeAuth.getStoreSecretkey())))) {
        } else {
            throw new OrderException(OrderCode.LOGIN_ERROR);
        }
    }
}
