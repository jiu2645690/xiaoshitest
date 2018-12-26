package com.xiaoshi.order.web.controller.api;


import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.FoodItemTemplateMenu;
import com.xiaoshi.order.pojo.dto.StoreList;
import com.xiaoshi.order.pojo.dto.StoreReview;
import com.xiaoshi.order.pojo.dto.UserLogin;
import com.xiaoshi.order.pojo.entity.*;
import com.xiaoshi.order.pojo.form.StoreForm;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.*;
import com.xiaoshi.order.util.LoginUtil;
import com.xiaoshi.order.util.ParameterCalibrationUtil;
import com.xiaoshi.order.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.xiaoshi.order.constant.OrderCode.INFO_NOT_EXISTS;

/**
 * 商店Controller
 */
@RestController
@EnableTransactionManagement
@RequestMapping("/v1")
public class StoreController {

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
    private ReviewPictureAssociationService reviewPictureAssociationService;

    /**
     * 编号：3-0-001,店铺列表
     */
    @GetMapping("/store")
    public ResponseEntity storeList(StoreForm storeForm) {
        PageModel pageModel = storeService.search(storeForm);
        return ResultUtil.success(pageModel.getList());
    }

    /**
     * 编号：3-0-002,获取店铺更多展示图片
     */
    @GetMapping("/store/pictures")
    public ResponseEntity storePictures(@RequestParam(value = "storeId", required = true) Integer storeId) {
        ParameterCalibrationUtil.IsEmpty(storeId);
        Map map = new HashMap();
        map.put("storeId", storeId);
        List<StorePictureAssociation> storePictureAssociationList = storePictureAssociationService.get(map);
        if (storePictureAssociationList == null) {
            throw new OrderException(INFO_NOT_EXISTS);
        }
        List<String> list = new ArrayList<String>();
        for (StorePictureAssociation storePictureAssociation : storePictureAssociationList) {
            list.add(storePictureAssociation.getPicture().getPictureUrl());
        }
        SalesSetting salesSetting = salesSettingService.get((long) storeId);
        if (salesSetting != null) {
            if (salesSetting.getPicture() != null) {
                return ResultUtil.success("list:" + list + ",pictureUrl:" + salesSetting.getPicture().getPictureUrl());
            } else {
                return ResultUtil.success("list:" + list);
            }
        } else {
            return ResultUtil.success("list:" + list);
        }
    }

    /**
     * 编号：3-0-003,店铺信息
     */
    @GetMapping("/store/{storeId}")
    public ResponseEntity storeList(@PathVariable Long storeId,
                                    @RequestParam(value = "token", required = true) String token) {
        StoreList storeList = storeService.search(storeId, token);
        return ResultUtil.success(storeList);
    }

    /**
     * 编号：3-0-004,特惠区
     */
    @GetMapping("/store/discounts")
    public ResponseEntity dishComboList(@RequestParam(value = "storeId", required = true) Long storeId,
                                        @RequestParam(value = "pageNum", required = false) Integer pageNum,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "token", required = false) String token) {
        ParameterCalibrationUtil.IsEmpty(storeId);
        if (storeService.get(storeId) == null) {
            throw new OrderException(INFO_NOT_EXISTS);
        }
        return ResultUtil.success(storeService.getDishComboList(storeId, token, pageNum, pageSize).getList());
    }

    /**
     * 编号：3-0-005,获取店铺今天和以后2两周内设置的营运日期，通过匹配今天是否营业，判断是否有菜品
     */
    @GetMapping("/store/menu")
    public ResponseEntity menuList(@RequestParam(value = "storeId", required = true) Long storeId,
                                        @RequestParam(value = "date", required = true) String date) {
        ParameterCalibrationUtil.IsEmpty(storeId);
        if (storeService.get(storeId) == null) {
            throw new OrderException(INFO_NOT_EXISTS);
        }
        String[] timeStr = ParameterCalibrationUtil.IsDateEmpty(date).split(",");
        Map map = new HashMap();
        map.put("storeId",storeId);
        map.put("frontDate", timeStr[0]);
        map.put("afterDate", timeStr[1]);
        List<OperatingSetting> operatingSettings = operatingSettingService.getOperatingSettingByParameter(map);
        List foodItemTemplateMenus=new ArrayList();
        if(operatingSettings == null)
            return ResultUtil.success("没有查到相关数据");
        if(operatingSettings.size() == 0)
            return ResultUtil.success("没有查到相关数据");
        for(OperatingSetting operatingSetting:operatingSettings){
            FoodItemTemplateMenu foodItemTemplateMenu = new FoodItemTemplateMenu();
            foodItemTemplateMenu.setCurrentFoodItemTemplateId(operatingSetting.getCurrentFoodItemTemplateId().getFoodItemTemplateId());
            foodItemTemplateMenu.setDate(operatingSetting.getDate());
            foodItemTemplateMenus.add(foodItemTemplateMenu);
        }
        return ResultUtil.success(foodItemTemplateMenus);
    }

    /**
     * 编号：3-0-006,店铺评价列表
     */
    @GetMapping("/store/review")
    public ResponseEntity reviewList(@RequestParam(value = "storeId", required = true) Long storeId,
                                     @RequestParam(value = "type", required = false, defaultValue = "1") Integer type,
                                     @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ParameterCalibrationUtil.IsEmpty(storeId);
        if (storeService.get(storeId) == null) {
            throw new OrderException(INFO_NOT_EXISTS);
        }
        String reviewScore[] = ParameterCalibrationUtil.reviewType(type).split("-");
        Map map = new HashMap();
        map.put("storeId", storeId);
        map.put("fReviewScore", reviewScore[0]);
        map.put("aReviewScore", reviewScore[1]);
        List<Review> reviews = reviewService.getReviewByParameter(map);
        List<StoreReview> storeReviews = new ArrayList<StoreReview>();
        if (reviews == null) {
            throw new OrderException(INFO_NOT_EXISTS);
        }
        if (reviews.size() == 0) {
            throw new OrderException(INFO_NOT_EXISTS);
        }
        for (Review review : reviews) {
            StoreReview storeReview = new StoreReview();
            storeReview.setReviewTime(review.getReviewTime());
            storeReview.setCustomerAvatarUrl(review.getCustomer().getPicture().getPictureUrl());
            storeReview.setCustomerNickName(review.getCustomer().getNickName());
            storeReview.setScoreContent(review.getScoreContent());
            List<ReviewPictureAssociation> reviewPictureAssociations = reviewPictureAssociationService.getReviewPictureAssociationByParameter(map);
            if (reviewPictureAssociations != null) {
                if (reviewPictureAssociations.size() > 0) {
                    List list = new ArrayList();
                    for (ReviewPictureAssociation reviewPictureAssociation : reviewPictureAssociations) {
                        list.add(reviewPictureAssociation.getPicture().getPictureUrl());
                    }
                    storeReview.setPictureList(list);
                }
            }
            storeReviews.add(storeReview);
        }
        return ResultUtil.success(storeReviews);
    }

    /**
     * 编号：3-0-007,获取根据currentFoodItemTemplateId 获取当前日期运营的菜品
     */
    @GetMapping("/store/foodItem/menu")
    public ResponseEntity dishFoodItemTemplateList(@RequestParam(value = "currentFoodItemTemplateId", required = true) Long currentFoodItemTemplateId,
                                        @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                        @RequestParam(value = "token", required = false) String token) {
        ParameterCalibrationUtil.IsEmpty(currentFoodItemTemplateId);
        return ResultUtil.success(storeService.getFoodItemTemplateList(currentFoodItemTemplateId, token, pageNum, pageSize).getList());
    }
}
