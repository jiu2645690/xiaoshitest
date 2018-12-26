package com.xiaoshi.order.web.controller.api;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.entity.*;
import com.xiaoshi.order.service.*;
import com.xiaoshi.order.util.ParameterCalibrationUtil;
import com.xiaoshi.order.util.ResultUtil;
import com.xiaoshi.order.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 商店Controller
 */
@RestController
@RequestMapping("/v1")
@EnableTransactionManagement
public class FavoriteController {

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
    private FoodItemService foodItemService;
    @Autowired
    private ComboTemplateService comboTemplateService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private ReviewPictureAssociationService reviewPictureAssociationService;
    private static String VERRIFICATIONCODE = "VERRIFICATIONCODE";

    /**
     * 编号：8-1-001,收藏夹统计顾客基本信息首页（用于“我”这个页面收藏夹总数统计）
     */
    @GetMapping("/favorite/statistics")
    public ResponseEntity favoriteStatistics(@RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("amount:"+favoriteService.getFavoriteStatistics(token));
    }

    /**
     * 编号：8-1-002,添加菜品/店铺/套餐到收藏夹
     */
    @PostMapping("/favorite/add")
    public ResponseEntity favoriteAdd(@RequestParam(value = "token", required = true) String token,
                                      @RequestParam(value = "foodItemId", required = false) Long foodItemId,
                                      @RequestParam(value = "storeId", required = false) Long storeId,
                                      @RequestParam(value = "comboTemplateId", required = false) Long comboTemplateId) {
        ParameterCalibrationUtil.IsEmpty(token);
        FoodItem foodItem=null;
        Store store=null;
        ComboTemplate comboTemplate=null;
        if(foodItemId != null)
            foodItem=foodItemService.get(foodItemId);
        if(storeId != null)
            store=storeService.get(storeId);
        if(comboTemplateId != null)
            comboTemplate=comboTemplateService.get(comboTemplateId);
        Favorite favorite =new Favorite();
        if(foodItem != null)
            favorite.setFoodItemId(foodItem);
        if(store != null)
            favorite.setStoreId(store);
        if(comboTemplate != null)
            favorite.setComboTemplateId(comboTemplate);
        Customer customer=TokenUtil.getTokenModel(token).getCustomer();
        if(customer != null)
            favorite.setCustomerId(customer);
        favoriteService.insertFavorite(favorite);
        return ResultUtil.success("status:"+true);
    }

    /**
     * 编号：8-0-003,获取收藏店铺列表
     */
    @GetMapping("/favorite/stores")
    public ResponseEntity favoriteStores(@RequestParam(value = "token", required = true) String token,
                                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("list:"+favoriteService.getFavoriteStores(token,pageNum,pageSize));
    }

    /**
     * 编号：8-0-004,获取收藏菜品列表
     */
    @GetMapping("/favorite/foodItems")
    public ResponseEntity favoriteFoodItems(@RequestParam(value = "token", required = true) String token,
                                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("list:"+favoriteService.getFavoriteFoodItems(token,pageNum,pageSize));
    }

    /**
     * 编号：8-0-005,获取收藏套餐列表
     */
    @GetMapping("/favorite/combos")
    public ResponseEntity favoriteCombos(@RequestParam(value = "token", required = true) String token,
                                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("list:"+favoriteService.getFavoriteCombos(token,pageNum,pageSize));
    }

    /**
     * 编号：8-1-006,取消 菜品/店铺/套餐 的收藏
     */
    @PutMapping("/favorite/update")
    public ResponseEntity favoriteUpdate(@RequestParam(value = "token", required = true) String token,
                                         @RequestParam(value = "foodItemId", required = false) Long foodItemId,
                                         @RequestParam(value = "storeId", required = false) Long storeId,
                                         @RequestParam(value = "comboTemplateId", required = false) Long comboTemplateId) {
        ParameterCalibrationUtil.IsEmpty(token);
        ComboTemplate comboTemplate=null;
        FoodItem foodItem=null;
        Store store=null;
        if(foodItemId==null && storeId==null && comboTemplateId==null )
            throw new OrderException(OrderCode.PARM_NOT_EMPTY);
        if(foodItemId != null)
            foodItem=foodItemService.get(foodItemId);
        if(storeId != null)
            store=storeService.get(storeId);
        if(comboTemplateId != null)
            comboTemplate=comboTemplateService.get(comboTemplateId);
        if(foodItem==null && store==null && comboTemplate==null )
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        if(foodItem != null)
            map.put("foodItemId",foodItem.getFoodItemId());
        if(store != null)
            map.put("storeId",store.getStoreId());
        if(comboTemplate != null)
            map.put("comboTemplateId",comboTemplate.getComboTemplateId());
        Customer customer=TokenUtil.getTokenModel(token).getCustomer();
        if(customer != null)
            map.put("customerId",customer.getCustomerId());
        favoriteService.deleteFavorite(map);
        return ResultUtil.success("status:"+true);
    }

}
