package com.xiaoshi.order.web.controller.api;

import com.xiaoshi.order.service.*;
import com.xiaoshi.order.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

/**
 *菜品Controller
 */
@EnableTransactionManagement
@RestController
@RequestMapping("/v1")
public class DishController {

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
    private ReviewPictureAssociationService reviewPictureAssociationService;

    /**
     * 编号：3-0-008,获取菜品详情
     */
    @GetMapping("/dish/{foodItemId}")
    public ResponseEntity foodItem(@PathVariable("foodItemId") Long foodItemId) {
        return ResultUtil.success(foodItemService.getFoodItem(foodItemId));
    }

    /**
     * 编号：3-0-009,获取更多菜品图片
     */
    @GetMapping("/dish/pictures/{foodItemId}")
    public ResponseEntity foodItemPictures(@PathVariable("foodItemId") Long foodItemId) {
        return ResultUtil.success(foodItemService.getFoodItemPicture(foodItemId));
    }
}
