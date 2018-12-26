package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.dto.FoodItemCart;
import com.xiaoshi.order.pojo.dto.ReturnFoodItem;
import com.xiaoshi.order.pojo.form.CartForm;
import com.xiaoshi.order.pojo.form.ComboForm;
import com.xiaoshi.order.pojo.form.FoodItemForm;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.pojo.entity.ShoppingCart;
import java.util.List;

public interface ShoppingCartService {
    ShoppingCart get(Long id);

    List<ShoppingCart> getShoppingCartList();

    PageModel search();

    ShoppingCart getShoppingCartByCustomerId(Long customerId);

    void insertShoppingCart(ShoppingCart shoppingCart);

    String comboIntoShoppingCart(ComboForm comboForm);

    String foodItemIntoShoppingCart(FoodItemForm foodItemForm);

    void updateShoppingCart(CartForm cartForm);

    Integer getShoppingCart(String token);

    List<FoodItemCart> getShoppingCartList(String token);

    Boolean deleteShoppingCart(String token, Long id);

    List<ReturnFoodItem>  getComboFoodItem(Long id);
}
