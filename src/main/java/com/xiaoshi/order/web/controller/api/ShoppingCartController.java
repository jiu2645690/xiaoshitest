package com.xiaoshi.order.web.controller.api;

import com.xiaoshi.order.pojo.form.CartForm;
import com.xiaoshi.order.pojo.form.FoodItemForm;
import com.xiaoshi.order.pojo.form.ComboForm;
import com.xiaoshi.order.service.*;
import com.xiaoshi.order.util.ParameterCalibrationUtil;
import com.xiaoshi.order.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 购物车Controller
 */
@RestController
@EnableTransactionManagement
@RequestMapping("/v1")
public class ShoppingCartController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SalesSettingService salesSettingService;
    @Autowired
    private StoreAddressAssociationService storeAddressAssociationService;
    @Autowired
    private StorePictureAssociationService storePictureAssociationService;
    @Autowired
    private OperatingSettingService operatingSettingService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private ShoppingCartLineItemService shoppingCartLineItemService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ComboAndfFoodItemAssociationService comboAndfFoodItemAssociationService;

    /**
     * 编号：4-1-001,将套餐添加到购物车
     */
    @Transactional
    @PostMapping("/store/combo")
    public ResponseEntity foodItemCombo(ComboForm comboForm) {
        return ResultUtil.success(shoppingCartService.comboIntoShoppingCart(comboForm));
    }

    /**
     * 编号：4-1-002,将菜品添加到购物车
     */
    @Transactional
    @PostMapping("/store/dish")
    public ResponseEntity foodItem(FoodItemForm foodItemForm) {
        return ResultUtil.success(shoppingCartService.foodItemIntoShoppingCart(foodItemForm));
    }

    /**
     * 编号：4-1-003,购物车变更数量
     */
    @Transactional
    @PutMapping("/cart")
    public ResponseEntity updateCart(CartForm cartForm) {
        shoppingCartService.updateShoppingCart(cartForm);
        return ResultUtil.success();
    }

    /**
     * 编号：4-1-004,购物车统计 顾客基本信息首页 （用于 “我” 这个页面 购物车数量的统计)
     */
    @GetMapping("/cart/statistics")
    public ResponseEntity cartStatistics(@RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("amount:" + shoppingCartService.getShoppingCart(token));
    }

    /**
     * 编号：4-1-006,获取购物车列表
     */
    @GetMapping("/cart")
    public ResponseEntity cartList(@RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success(shoppingCartService.getShoppingCartList(token));
    }

    /**
     * 编号：4-1-007,获取套餐中的菜品
     */
    @GetMapping("/store/combo/dish")
    public ResponseEntity comboDish(@RequestParam(value = "comboId", required = true) Long comboId) {
        ParameterCalibrationUtil.IsEmpty(comboId);
        return ResultUtil.success(shoppingCartService.getComboFoodItem(comboId));
    }

    /**
     * 编号：4-1-005,删除购物车中的商品
     */
    @DeleteMapping("/cart")
    public ResponseEntity cartStatistics(@RequestParam(value = "token", required = true) String token,
                                         @RequestParam(value = "shoppingCartLineItemIdList", required = true) List<Long> shoppingCartLineItemIdList) {
        ParameterCalibrationUtil.IsEmpty(token);
        ParameterCalibrationUtil.IsEmpty(shoppingCartLineItemIdList);
        Boolean flag = true;
        for (int i = 0; i < shoppingCartLineItemIdList.size(); i++) {
            try {
                shoppingCartService.deleteShoppingCart(token, shoppingCartLineItemIdList.get(i));
            } catch (Exception e) {
                flag = false;
            }
        }
        return ResultUtil.success(flag);
    }
}
