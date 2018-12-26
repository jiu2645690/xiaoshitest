package com.xiaoshi.order.web.controller.api;

import com.xiaoshi.order.pojo.form.AddressAndStateForm;
import com.xiaoshi.order.pojo.form.OrderStoreForm;
import com.xiaoshi.order.pojo.form.OrderVerifyForm;
import com.xiaoshi.order.pojo.form.ReviewForm;
import com.xiaoshi.order.service.*;
import com.xiaoshi.order.util.ParameterCalibrationUtil;
import com.xiaoshi.order.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *菜品Controller
 */
@EnableTransactionManagement
@RestController
@RequestMapping("/v1")
public class OrderController {

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
    private OrderService orderService;
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private ReviewPictureAssociationService reviewPictureAssociationService;

    /**
     * 编号：5-0-001,请求-生成预订单，单个下单也放list里
     */
    @Transactional
    @PostMapping("/order/customer")
    public ResponseEntity toOrder(@RequestParam(value = "shoppingCartLineItemIdList", required = true) List<Long> shoppingCartLineItemIdList) {
        ParameterCalibrationUtil.IsEmpty(shoppingCartLineItemIdList);
        return ResultUtil.success("list:"+orderService.toOrder(shoppingCartLineItemIdList));
    }

    /**
     * 编号：5-0-002,详情 -获取 基本信息（如果是已完成订单，点击后变更读取状态）
     */
    @GetMapping("/order/info/{orderId}")
    public ResponseEntity toOrderInfo(@PathVariable("orderId") Long orderId) {
        ParameterCalibrationUtil.IsEmpty(orderId);
        return ResultUtil.success(orderService.toOrderInfo(orderId));
    }

     /**
     * 编号：5-0-003,详情 -获取菜品列表套餐列表
     */
    @GetMapping("/order/meals/{orderId}")
    public ResponseEntity toOrderMeals(@PathVariable("orderId") Long orderId,
                                       @RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsTokenEmpty(token);
        ParameterCalibrationUtil.IsEmpty(orderId);
        return ResultUtil.success("list:"+orderService.toOrderCombotList(orderId, token));
    }

    /**
     * 编号：5-0-004,变更数量
     */
    @Transactional
    @PutMapping("/order")
    public ResponseEntity updateOrderSum(@RequestParam(value ="orderId", required = true) Long orderId,
                                         @RequestParam(value ="comboId", required = false) Long comboId,
                                         @RequestParam(value ="count", required = true) Integer count,
                                         @RequestParam(value = "foodItemId", required = false) Long foodItemId) {
        ParameterCalibrationUtil.IsEmpty(orderId);
        ParameterCalibrationUtil.IsEmpty(count);
        return ResultUtil.success("status:"+orderService.updateOrderSum(orderId,comboId,foodItemId,count));
    }

    /**
     * 编号：5-0-005,补全订单信息  验证下单
     */
    @GetMapping("/order/verify")
    public ResponseEntity verifyOrder(@RequestParam(value ="token", required = true) String token,
                                      @RequestParam(value = "list", required = false) List<OrderVerifyForm> list) {
        ParameterCalibrationUtil.IsTokenEmpty(token);
//        List<OrderVerifyForm> lists=new ArrayList<>();
//        OrderVerifyForm orderVerifyForm=new OrderVerifyForm();
//        orderVerifyForm.setDeliveryDate(new Date());
//        orderVerifyForm.setDeliveryTime(1);
//        orderVerifyForm.setOrderId(1L);
//        orderVerifyForm.setTip(new BigDecimal(11));
//        orderVerifyForm.setRemark("好吃");
//        orderVerifyForm.setTaxes(new BigDecimal(2));
//        lists.add(orderVerifyForm);
        return ResultUtil.success("status:"+orderService.verifyOrder(token,list));
    }

    /**
     * 编号：5-0-006,获取所有订单分页排序
     */
    @GetMapping("/order")
    public ResponseEntity getOrder(@RequestParam(value ="token", required = true) String token,
                                   @RequestParam(value ="orderState", required = true) Integer orderState,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ParameterCalibrationUtil.IsEmpty(token);
        ParameterCalibrationUtil.IsEmpty(orderState);
        return ResultUtil.success("list:"+orderService.getOrderList(token,pageNum,pageSize,orderState));
    }

    /**
     * 编号：5-0-007,获取所有订单分页排序
     */
    @PostMapping("/review")
    public ResponseEntity toreview(ReviewForm peviewForm) {
        return ResultUtil.success("status:"+orderService.toReview(peviewForm));
    }

    /**
     * 编号：5-0-0011,获取商家所有订单分页排序
     */
    @GetMapping("/order/store")
    public ResponseEntity orderStoreForm(OrderStoreForm orderStoreForm) {
        return ResultUtil.success("list:"+orderService.getOrderStoreList(orderStoreForm));
    }

    /**
     * 编号：5-0-0012,根据所选地址订单一建送达
     */
    @PutMapping("/order/delivery")
    public ResponseEntity orderDelivery(@RequestParam(value = "token", required = true) String token,
                                        @RequestParam(value = "list", required = false) List<AddressAndStateForm> list) {
        return ResultUtil.success("status:"+orderService.getOrderdelivery(token,list));
    }

    /**
     * 编号：5-0-0013,变更单个订单的送达状态/同意退款
     */
    @PutMapping("/order/stateChange")
    public ResponseEntity orderStateChange(@RequestParam(value = "token", required = true) String token,
                                           @RequestParam(value = "state", required = true) Integer state,
                                           @RequestParam(value = "orderId", required = true) Long orderId) {
        ParameterCalibrationUtil.IsEmpty(token);
        ParameterCalibrationUtil.IsEmpty(state);
        ParameterCalibrationUtil.IsEmpty(orderId);
        return ResultUtil.success("status:"+orderService.orderStateChange(token,state,orderId));
    }

    /**
     * 编号：5-0-0014,商家删除订单
     */
    @DeleteMapping("/order/sotreDelete")
    public ResponseEntity orderSotreDelete(@RequestParam(value = "token", required = true) String token,
                                           @RequestParam(value = "orderId", required = true) Long orderId) {
        ParameterCalibrationUtil.IsEmpty(token);
        ParameterCalibrationUtil.IsEmpty(orderId);
        return ResultUtil.success("status:"+orderService.orderSotreDelete(token,orderId));
    }

    /**
     * 编号：5-0-0015,点击日历中某一天查看今天需要备的菜
     */
    @GetMapping("/order/fooditemStatistics")
    public ResponseEntity fooditemStatistics(@RequestParam(value = "token", required = true) String token,
                                             @RequestParam(value = "date", required = true) Date date) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("list:"+orderService.fooditemStatistics(token,date));
    }
    /**
     * 编号：5-0-0016,点击日历中某一天查看今天需要备的菜
     */
    @GetMapping("/order/nowFooditemStatistics")
    public ResponseEntity nowFooditemStatistics(@RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("list:"+orderService.nowFooditemStatistics(token));
    }


    /**
     * 编号：5-0-0010,再来一单
     */
    @Transactional
    @PostMapping("/order/onemore")
    public ResponseEntity oneMoreOrder(@RequestParam(value = "token", required = true) String token,
                                       @RequestParam(value = "orderId", required = true) Long orderId) {
        ParameterCalibrationUtil.IsEmpty(token);
        ParameterCalibrationUtil.IsEmpty(orderId);
        return ResultUtil.success("orderId:"+orderService.oneMoreOrder(token, orderId));
    }
}
