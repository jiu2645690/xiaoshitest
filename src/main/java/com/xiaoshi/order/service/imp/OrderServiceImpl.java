package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.dao.*;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.*;
import com.xiaoshi.order.pojo.entity.*;
import com.xiaoshi.order.pojo.form.AddressAndStateForm;
import com.xiaoshi.order.pojo.form.OrderStoreForm;
import com.xiaoshi.order.pojo.form.OrderVerifyForm;
import com.xiaoshi.order.pojo.form.ReviewForm;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.OrderService;
import com.xiaoshi.order.util.OperatingSettingUtil;
import com.xiaoshi.order.util.PageUtil;
import com.xiaoshi.order.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

/**
 *  OrderServiceImpl
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ShoppingCartLineItemDao shoppingCartLineItemDao;
    @Autowired
    private ComboAndfFoodItemAssociationDao comboAndfFoodItemAssociationDao;
    @Autowired
    private OrderAndFoodItemComboAssociationDao orderAndFoodItemComboAssociationDao;
    @Autowired
    private OperatingSettingDao operatingSettingDao;
    @Autowired
    private FoodItemAndTemplateAssociationDao foodItemAndTemplateAssociationDao;
    @Autowired
    private ReviewDao reviewDao;

    /**
     * 通过id获取 Order
     */
    @Override
    public Order get(Long id) {
        return orderDao.select(id);
    }

    /**
     * 获取 Order列表
     */
    @Override
    public List<Order> getOrderList() {
        return orderDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<Order> list = orderDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 Order
     */
    @Override
    public void insertOrder(Order order) {
        orderDao.insert(order);
    }

    /**
     * 更新Order
     */
    @Override
    public void updateOrder(Order order){
        orderDao.updateOrder(order);
    }

    /**
     * 请求-生成预订单，单个下单也放list里
     */
    @Override
    @Transactional
    public List<Long> toOrder(List<Long> list) {
        List<Long> lists=new ArrayList<Long>();
        for(int i=0;i<list.size();i++){
            ShoppingCartLineItem shoppingCartLineItem= shoppingCartLineItemDao.select(list.get(i));
            OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation=new OrderAndFoodItemComboAssociation();
            Order order = new Order();
            order.setCreatedAt(new Date());
            order.setUuId(UUID.randomUUID().toString());
            order.setDeliveryDate(shoppingCartLineItem.getDeliveryDate());
            order.setDeliveryTime(shoppingCartLineItem.getDeliveryTime());
            order.setCustomer(shoppingCartLineItem.getShoppingCart().getCustomer());
            order.setStore(shoppingCartLineItem.getStore());
            order.setAddress(shoppingCartLineItem.getShoppingCart().getCustomer().getAddress());
            BigDecimal orderMoney =new BigDecimal(0);
            //计算菜品价格
            if(shoppingCartLineItem.getFoodItem()!=null){
                orderAndFoodItemComboAssociation.setFoodItem(shoppingCartLineItem.getFoodItem());
                orderAndFoodItemComboAssociation.setFoodItemCount(shoppingCartLineItem.getFoodItemCount());
                if(shoppingCartLineItem.getFoodItem().getDailySpecialPrice().compareTo(BigDecimal.ZERO)==0){
                    //菜品价格*菜品数
                    orderMoney=shoppingCartLineItem.getFoodItem().getPrice().multiply(new BigDecimal(shoppingCartLineItem.getFoodItemCount()));
                }else{
                    //特价菜品价格*菜品数
                    orderMoney=shoppingCartLineItem.getFoodItem().getDailySpecialPrice().multiply(new BigDecimal(shoppingCartLineItem.getFoodItemCount()));
                }
            }
            //计算套餐价格
            if(shoppingCartLineItem.getCombo()!=null){
                orderAndFoodItemComboAssociation.setCombo(shoppingCartLineItem.getCombo());
                orderAndFoodItemComboAssociation.setComboTemplateCount(shoppingCartLineItem.getComboCount().intValue());
                Map map = new HashMap();
                map.put("comboId", shoppingCartLineItem.getCombo().getComboId());
                //通过菜品模板获取菜品
                List<ComboAndfFoodItemAssociation> comboAndfFoodItemAssociations = comboAndfFoodItemAssociationDao.getComboAndfFoodItemAssociationByParameter(map);
                for(ComboAndfFoodItemAssociation comboAndfFoodItemAssociation:comboAndfFoodItemAssociations){
                    if(comboAndfFoodItemAssociation.getFoodItem().getDailySpecialPrice().compareTo(BigDecimal.ZERO)==0){
                        //菜品价格*套餐数*菜品数*折扣
                        orderMoney=comboAndfFoodItemAssociation.getFoodItem().getPrice().multiply(new BigDecimal(shoppingCartLineItem.getComboCount()*comboAndfFoodItemAssociation.getFoodItemCount()*shoppingCartLineItem.getCombo().getComboTemplate().getComboTemplateDiscount()));
                    }else{
                        //特价菜品价格*套餐数*菜品数*折扣
                        orderMoney=comboAndfFoodItemAssociation.getFoodItem().getDailySpecialPrice().multiply(new BigDecimal(shoppingCartLineItem.getComboCount()*comboAndfFoodItemAssociation.getFoodItemCount()*shoppingCartLineItem.getCombo().getComboTemplate().getComboTemplateDiscount()));
                    }
                }
            }
            //菜品或套餐价格
            order.setSubTotal(orderMoney);
            //税收
            order.setSubTotal(orderMoney.multiply(new BigDecimal(0.1)));
            //小费
            order.setSubTotal(new BigDecimal(0));
            //总金额
            order.setTotalAmount(orderMoney.add(orderMoney.multiply(new BigDecimal(0.1))).add(new BigDecimal(0)));
            //商家收益=菜品金额*（1-当前的系统抽成比例）
            order.setStoreEarnings(orderMoney.multiply(new BigDecimal(1).subtract(new BigDecimal(0))));
            //平台收益=菜品金额总计*当前的系统抽成比例
            order.setPlatformEarnings(orderMoney.multiply(new BigDecimal(0)));
            //系统抽成比例
            order.setProportional(new BigDecimal(0));
            orderDao.insert(order);
            orderAndFoodItemComboAssociation.setOrder(order);
            orderAndFoodItemComboAssociationDao.insert(orderAndFoodItemComboAssociation);
            lists.add(order.getOrderId());
        }
        return lists;
    }

    /**
     * 详情 -获取基本信息（如果是已完成订单，点击后变更读取状态）
     */
    @Override
    public OrderInfo toOrderInfo(Long id) {
        Order order=orderDao.select(id);
        if(order==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        if(order.getState()==4){
            //如果是已完成订单，点击后变更读取状态
            order.setCustomerIsRead(true);
            orderDao.updateOrder(order);
        }
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setAddressName(order.getAddress().getAddressName());
        orderInfo.setDeliveryDate(order.getDeliveryDate());
        orderInfo.setDeliveryTime(order.getDeliveryTime());
        orderInfo.setRefundReason(order.getRefundReason());
        orderInfo.setRemark(order.getRemark());
        orderInfo.setSotreName(order.getStore().getStoreName());
        orderInfo.setTaxes(order.getTaxes().toString());
        orderInfo.setTip(order.getTip().toString());
        orderInfo.setTotalAmount(order.getTotalAmount().toString());
        return orderInfo;
    }

    /**
     * 详情 -获取 菜品列表 套餐列表
     */
    @Override
    public List<CombotList> toOrderCombotList(Long orderId, String token) {
        List<CombotList> combotLists=new ArrayList<CombotList>();
        TokenUtil.getTokenModel(token);
        Order order=orderDao.select(orderId);
        if(order==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map = new HashMap();
        map.put("orderId", orderId);
        //通过菜品模板获取菜品
        List<OrderAndFoodItemComboAssociation> orderAndFoodItemComboAssociations = orderAndFoodItemComboAssociationDao.getOrderAndFoodItemComboAssociationByParameter(map);
        if(orderAndFoodItemComboAssociations==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        for(OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation:orderAndFoodItemComboAssociations){
            CombotList combotList=new CombotList();
            if(orderAndFoodItemComboAssociation.getCombo()!=null){
                combotList.setComboCount(orderAndFoodItemComboAssociation.getComboTemplateCount());
                combotList.setComboId(orderAndFoodItemComboAssociation.getCombo().getComboId());
            }
            if(orderAndFoodItemComboAssociation.getFoodItem()!=null){
                combotList.setFoodItemCount(orderAndFoodItemComboAssociation.getFoodItemCount());
                combotList.setFoodItemId(orderAndFoodItemComboAssociation.getFoodItem().getFoodItemId());
            }
            combotLists.add(combotList);
        }
        return combotLists;
    }

    /**
     * 获取所有订单分页排序
     */
    @Override
    public List<OrderList> getOrderList(String token, Integer pageNum, Integer pageSize, Integer orderState) {
        Customer customer=TokenUtil.getTokenModel(token).getCustomer();
        List<OrderList> orderLists=new ArrayList<OrderList>();
        if(customer==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        map.put("customerId",customer.getCustomerId());
        if(orderState==1){
            map.put("state",1);
        }else if(orderState==2){
            map.put("state",3);
        }else if(orderState==3){
            map.put("state",4);
        }else if(orderState==4){
            map.put("state",5);
        }
        List<Order> orders=orderDao.getOrderByParameter(map);
        if(orders==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        if(orders.size()==0)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        for(Order order:orders){
            OrderList orderList =new OrderList();
            orderList.setStoreName(order.getStore().getStoreName());
            orderList.setTotalAmount(order.getTotalAmount());
            orderList.setShortContent(order.getRemark());
            orderLists.add(orderList);
        }
        return PageUtil.setPage(orderLists,PageUtil.setPage(pageNum, pageSize)).getList();
    }

    /**
     * 商家 点击日历中某一天 查看今天需要备的菜
     */
    @Override
    public List<FoodItemName> fooditemStatistics(String token, Date date) {
        Store store=TokenUtil.getTokenModel(token).getStore();
        List<FoodItemName> foodItemLists=new ArrayList<FoodItemName>();
        if(store==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        OperatingSetting operatingSetting=OperatingSettingUtil.getOperatingSetting(date,store.getStoreId());
        if(operatingSetting==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        map.put("foodItemTemplateId",operatingSetting.getCurrentFoodItemTemplateId());
        List<FoodItemAndTemplateAssociation> foodItemAndTemplateAssociations=foodItemAndTemplateAssociationDao.getFoodItemAndTemplateAssociationByParameter(map);
        if(foodItemAndTemplateAssociations==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        if(foodItemAndTemplateAssociations.size()==0)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        for(FoodItemAndTemplateAssociation foodItemAndTemplateAssociation:foodItemAndTemplateAssociations){
            FoodItemName foodItemList=new FoodItemName();
            foodItemList.setName(foodItemAndTemplateAssociation.getFoodItem().getName());
            foodItemList.setFoodItemId(foodItemAndTemplateAssociation.getFoodItem().getFoodItemId());
            foodItemLists.add(foodItemList);
        }
        return foodItemLists;
    }

    /**
     * 商家  查看今天 菜品统计。
     */
    @Override
    public List<FoodItemName> nowFooditemStatistics(String token) {
        Store store=TokenUtil.getTokenModel(token).getStore();
        List<FoodItemName> foodItemNames=new ArrayList<FoodItemName>();
        if(store==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        OperatingSetting operatingSetting=OperatingSettingUtil.getOperatingSetting(new Date(),store.getStoreId());
        if(operatingSetting==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        map.put("foodItemTemplateId",operatingSetting.getCurrentFoodItemTemplateId());
        List<FoodItemAndTemplateAssociation> foodItemAndTemplateAssociations=foodItemAndTemplateAssociationDao.getFoodItemAndTemplateAssociationByParameter(map);
        if(foodItemAndTemplateAssociations==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        if(foodItemAndTemplateAssociations.size()==0)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        for(FoodItemAndTemplateAssociation foodItemAndTemplateAssociation:foodItemAndTemplateAssociations){
            FoodItemName foodItemName=new FoodItemName();
            foodItemName.setName(foodItemAndTemplateAssociation.getFoodItem().getName());
            foodItemName.setFoodItemId(foodItemAndTemplateAssociation.getFoodItem().getFoodItemId());
            foodItemNames.add(foodItemName);
        }
        return foodItemNames;
    }

    /**
     * 获取商家所有订单分页排序
     */
    @Override
    public List<OrderStore> getOrderStoreList(OrderStoreForm orderStoreForm) {
        List<OrderStore> orderStores=new ArrayList<OrderStore>();
        if(orderStoreForm.getPageNum()==null)
            orderStoreForm.setPageNum(1);
        if(orderStoreForm.getPageSize()==null)
            orderStoreForm.setPageSize(10);
        Store store=TokenUtil.getTokenModel(orderStoreForm.getToken()).getStore();
        if(store==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        map.put("storeId",store.getStoreId());
        if(orderStoreForm.getOrderState()==1){
            map.put("state",1);
        }else if(orderStoreForm.getOrderState()==2){
            map.put("state",3);
        }else if(orderStoreForm.getOrderState()==3){
            map.put("state",4);
        }else if(orderStoreForm.getOrderState()==4){
            map.put("state",5);
        }
        List<Order> orders=orderDao.getOrderByParameter(map);
        if(orders==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        if(orders.size()==0)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        for(Order order:orders){
            OrderStore orderStore =new OrderStore();
            orderStore.setDeliveryDate(order.getDeliveryDate());
            orderStore.setDeliveryTime(order.getDeliveryTime());
            orderStore.setRemark(order.getRemark());
            orderStore.setTotalAmount(order.getTotalAmount());
            orderStore.setUuId(order.getUuId());
            orderStores.add(orderStore);
        }
        return PageUtil.setPage(orderStores,PageUtil.setPage(orderStoreForm.getPageNum(), orderStoreForm.getPageSize())).getList();
    }

    /**
     * 订单预览,变更数量
     */
    @Override
    public Boolean updateOrderSum(Long orderId, Long comboId, Long foodItemId, Integer count) {
        if(comboId==null && foodItemId==null)
            throw new OrderException(OrderCode.PARM_NOT_EMPTY);
        Map map = new HashMap();
        map.put("orderId", orderId);
        Order order=orderDao.select(orderId);
        List<OrderAndFoodItemComboAssociation> orderAndFoodItemComboAssociations = orderAndFoodItemComboAssociationDao.getOrderAndFoodItemComboAssociationByParameter(map);
        if(orderAndFoodItemComboAssociations==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        if(orderAndFoodItemComboAssociations.size()==0)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        for(OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation:orderAndFoodItemComboAssociations) {
            BigDecimal orderMoney =new BigDecimal(0);
            if(orderAndFoodItemComboAssociation.getCombo()!=null){
                orderAndFoodItemComboAssociation.setComboTemplateCount(count);
                Map maptemp = new HashMap();
                maptemp.put("comboId", orderAndFoodItemComboAssociation.getCombo().getComboId());
                //通过菜品模板获取菜品
                List<ComboAndfFoodItemAssociation> comboAndfFoodItemAssociations = comboAndfFoodItemAssociationDao.getComboAndfFoodItemAssociationByParameter(map);
                for(ComboAndfFoodItemAssociation comboAndfFoodItemAssociation:comboAndfFoodItemAssociations){
                    if(comboAndfFoodItemAssociation.getFoodItem().getDailySpecialPrice().compareTo(BigDecimal.ZERO)==0){
                        //菜品价格*套餐数*菜品数*折扣
                        orderMoney=comboAndfFoodItemAssociation.getFoodItem().getPrice().multiply(new BigDecimal(orderAndFoodItemComboAssociation.getComboTemplateCount()*comboAndfFoodItemAssociation.getFoodItemCount()*comboAndfFoodItemAssociation.getCombo().getComboTemplate().getComboTemplateDiscount()));
                    }else{
                        //特价菜品价格*套餐数*菜品数*折扣
                        orderMoney=comboAndfFoodItemAssociation.getFoodItem().getDailySpecialPrice().multiply(new BigDecimal(orderAndFoodItemComboAssociation.getComboTemplateCount()*comboAndfFoodItemAssociation.getFoodItemCount()*comboAndfFoodItemAssociation.getCombo().getComboTemplate().getComboTemplateDiscount()));
                    }
                }

            }
            if(orderAndFoodItemComboAssociation.getFoodItem()!=null){
                orderAndFoodItemComboAssociation.setFoodItemCount(count);
                if(orderAndFoodItemComboAssociation.getFoodItem().getDailySpecialPrice().compareTo(BigDecimal.ZERO)==0){
                    //菜品价格*菜品数
                    orderMoney=orderAndFoodItemComboAssociation.getFoodItem().getPrice().multiply(new BigDecimal(orderAndFoodItemComboAssociation.getFoodItemCount()));
                }else{
                    //特价菜品价格*菜品数
                    orderMoney=orderAndFoodItemComboAssociation.getFoodItem().getDailySpecialPrice().multiply(new BigDecimal(orderAndFoodItemComboAssociation.getFoodItemCount()));
                }
            }
            order.setSubTotal(orderMoney);
            order.setTotalAmount(orderMoney.add(order.getTaxes()).add(order.getTip()));
            order.setLastUpdatedAt(new Date());
            orderDao.updateOrder(order);
            orderAndFoodItemComboAssociationDao.updateOrderAndFoodItemComboAssociation(orderAndFoodItemComboAssociation);
        }
        return true;
    }

    /**
     * 补全订单信息,验证下单
     */
    @Override
    public Boolean verifyOrder(String token,List<OrderVerifyForm> list) {
        TokenUtil.getTokenModel(token);
        if(list==null)
            throw new OrderException(OrderCode.PARM_NOT_EMPTY);
        if(list.size()==0)
            throw new OrderException(OrderCode.PARM_NOT_EMPTY);
        for(OrderVerifyForm orderVerifyForm:list){
            if(orderVerifyForm.getOrderId()==null)
                throw new OrderException(OrderCode.PARM_NOT_EMPTY);
            if(orderVerifyForm.getDeliveryDate()==null)
                throw new OrderException(OrderCode.PARM_NOT_EMPTY);
            if(orderVerifyForm.getDeliveryTime()==null)
                throw new OrderException(OrderCode.PARM_NOT_EMPTY);
            if(orderVerifyForm.getRemark()==null)
                throw new OrderException(OrderCode.PARM_NOT_EMPTY);
            if(orderVerifyForm.getTip()==null)
                throw new OrderException(OrderCode.PARM_NOT_EMPTY);
            Order order=orderDao.select(orderVerifyForm.getOrderId());
            if(order==null)
                throw new OrderException(OrderCode.INFO_NOT_EXISTS);
            //获取当日销售的operatingSetting
            OperatingSetting operatingSetting=OperatingSettingUtil.getOperatingSetting(orderVerifyForm.getDeliveryDate(),order.getStore().getStoreId());
            //判断产品数量和日期、时段等是否符合要求
            Map map = new HashMap();
            map.put("orderId", order.getOrderId());
            List<OrderAndFoodItemComboAssociation> orderAndFoodItemComboAssociations = orderAndFoodItemComboAssociationDao.getOrderAndFoodItemComboAssociationByParameter(map);
            if(orderAndFoodItemComboAssociations==null)
                throw new OrderException(OrderCode.INFO_NOT_EXISTS);
            if(orderAndFoodItemComboAssociations.size()==0)
                throw new OrderException(OrderCode.INFO_NOT_EXISTS);
            for(OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation:orderAndFoodItemComboAssociations) {
                Combo combo=orderAndFoodItemComboAssociation.getCombo();
                if(combo==null)
                    throw new OrderException(OrderCode.INFO_NOT_EXISTS);
                //判断套餐
                if(orderAndFoodItemComboAssociation.getCombo()!=null){
                    //判断套餐数量
                    if(!(orderAndFoodItemComboAssociation.getComboTemplateCount()<combo.getComboTemplate().getComboTemplateRemainingCount()))
                        throw new OrderException(OrderCode.COMBOSUM_NOT_EXISTS);
                    //判断套餐中的菜品
                    Map comboAndfFoodItemAssociationmap = new HashMap();
                    comboAndfFoodItemAssociationmap.put("comboId",orderAndFoodItemComboAssociation.getCombo().getComboId());
                    List<ComboAndfFoodItemAssociation> comboAndfFoodItemAssociations=comboAndfFoodItemAssociationDao.getComboAndfFoodItemAssociationByParameter(comboAndfFoodItemAssociationmap);
                    if(comboAndfFoodItemAssociations==null)
                        throw new OrderException(OrderCode.COMBOINFOOD_NOT_EXISTS);
                    if(comboAndfFoodItemAssociations.size()==0)
                        throw new OrderException(OrderCode.COMBOINFOOD_NOT_EXISTS);
                    for(ComboAndfFoodItemAssociation comboAndfFoodItemAssociation:comboAndfFoodItemAssociations){
                        //判断菜品是否在当日OperatingSetting中
                       OperatingSettingUtil.isfoodItemAndTemplateAssociations(operatingSetting.getCurrentFoodItemTemplateId().getFoodItemTemplateId(),comboAndfFoodItemAssociation.getFoodItem().getFoodItemId());
                        //判断菜品上下架ture下架flase未下架
                        if(comboAndfFoodItemAssociation.getFoodItem().getIsAvailable())
                            throw new OrderException(OrderCode.FOOD_NOT_DOWN);
                        //判断菜品剩余数量
                        if(comboAndfFoodItemAssociation.getFoodItem().getRemainingCount()<=0)
                            throw new OrderException(OrderCode.FOODSUM_NOT_EXISTS);
                        //判断菜品剩余数量-菜品中的订单数是否>0
                        if((comboAndfFoodItemAssociation.getFoodItem().getRemainingCount()-comboAndfFoodItemAssociation.getFoodItemCount())<0)
                            throw new OrderException(OrderCode.FOODSUM_NOT_EXISTS);
                    }
                }
                //判断菜品
                if(orderAndFoodItemComboAssociation.getFoodItem()!=null){
                    //判断菜品是否在当日OperatingSetting中
                    OperatingSettingUtil.isfoodItemAndTemplateAssociations(operatingSetting.getCurrentFoodItemTemplateId().getFoodItemTemplateId(),orderAndFoodItemComboAssociation.getFoodItem().getFoodItemId());
                    //判断菜品上下架ture下架flase未下架
                    if(orderAndFoodItemComboAssociation.getFoodItem().getIsAvailable())
                        throw new OrderException(OrderCode.FOOD_NOT_DOWN);
                    //判断菜品剩余数量
                    if(orderAndFoodItemComboAssociation.getFoodItem().getRemainingCount()<=0)
                        throw new OrderException(OrderCode.FOODSUM_NOT_EXISTS);
                }
            }
            //订单验证结束，修改订单
            order.setDeliveryDate(orderVerifyForm.getDeliveryDate());
            order.setDeliveryTime(orderVerifyForm.getDeliveryTime());
            order.setLastUpdatedAt(new Date());
            order.setRemark(orderVerifyForm.getRemark());
            order.setTip(orderVerifyForm.getTip());
            order.setTotalAmount(order.getSubTotal().add(orderVerifyForm.getTip()).add(order.getTaxes()));
            orderDao.updateOrder(order);
        }
        return true;
    }

    /**
     * 对商家进行评价
     */
    @Override
    public Boolean toReview(ReviewForm reviewForm) {
        Review review=new Review();
        review.setCustomer(TokenUtil.getTokenModel(reviewForm.getToken()).getCustomer());
        Order order=orderDao.select(reviewForm.getOrderId());
        if(order==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        //如果订单为待评价状态才能评价
        if(order.getState()==3){
            review.setOrder(order);
            review.setStore(order.getStore());
            review.setReviewTime(new Date());
            review.setScoreContent(reviewForm.getScoreContent());
            review.setServiceScore(reviewForm.getServiceScore());
            review.setWeightScore(reviewForm.getWeightScore());
            review.setTasteScore(reviewForm.getTasteScore());
            review.setAvgScore((reviewForm.getServiceScore()+reviewForm.getWeightScore()+reviewForm.getTasteScore())/3);
            reviewDao.insert(review);
        }else{
            throw new OrderException(OrderCode.ORDERSTATE_NOT_EXISTS);
        }
        return true;
    }

    /**
     * 根据所选地址订单一建送达
     */
    @Override
    public Boolean getOrderdelivery(String token, List<AddressAndStateForm> list) {
        Store store=TokenUtil.getTokenModel(token).getStore();
        if(list==null)
            throw new OrderException(OrderCode.PARM_NOT_EMPTY);
        if(list.size()==0)
            throw new OrderException(OrderCode.PARM_NOT_EMPTY);
        if(store==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        for(AddressAndStateForm addressAndStateForm:list){
            Map map=new HashMap();
            map.put("storeId",store.getStoreId());
            map.put("addressId",addressAndStateForm.getAddressId());
            map.put("state",0);
            //修改订单状态
            List<Order> orders=orderDao.getOrderByParameter(map);
            if(orders!=null){
                for(Order order:orders){
                    order.setStoreIsRead(true);
                    order.setState(1);
                    orderDao.updateOrder(order);
                }
            }
        }
        return true;
    }

    /**
     * 变更单个订单的送达状态/同意退款
     */
    @Override
    public Boolean orderStateChange(String token, Integer state, Long orderId) {
        Store store=TokenUtil.getTokenModel(token).getStore();
        if(store==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        //修改订单状态
        Order order = orderDao.select(orderId);
        if (order != null) {
            order.setStoreIsRead(true);
            order.setState(state);
            orderDao.updateOrder(order);
        }
        return true;
    }

    /**
     * 商家删除订单
     */
    @Override
    public Boolean orderSotreDelete(String token, Long orderId) {
        Store store=TokenUtil.getTokenModel(token).getStore();
        if(store==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        //删除订单
        Order order = orderDao.select(orderId);
        try {
            orderDao.deleteOrderById(order.getOrderId());
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 再来一单
     */
    @Override
    public Long oneMoreOrder(String token, Long orderId) {
        Customer customer=TokenUtil.getTokenModel(token).getCustomer();
        Order order=orderDao.select(orderId);
        if(order==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Order ordertemp=new Order();
        ordertemp.setProportional(order.getProportional());
        ordertemp.setStoreEarnings(order.getStoreEarnings());
        ordertemp.setStore(order.getStore());
        ordertemp.setPlatformEarnings(order.getPlatformEarnings());
        ordertemp.setTotalAmount(order.getTotalAmount());
        ordertemp.setSubTotal(order.getSubTotal());
        ordertemp.setDeliveryDate(order.getDeliveryDate());
        ordertemp.setUuId(UUID.randomUUID().toString());
        ordertemp.setLastUpdatedAt(new Date());
        ordertemp.setCreatedAt(new Date());
        ordertemp.setCustomer(customer);
        ordertemp.setAddress(order.getAddress());
        ordertemp.setRemark(order.getRemark());
        ordertemp.setTip(order.getTip());
        ordertemp.setState(0);
        ordertemp.setTaxes(order.getTaxes());
        ordertemp.setDeliveryTime(order.getDeliveryTime());
        orderDao.insert(ordertemp);
        Map map = new HashMap();
        map.put("orderId", orderId);
        //通过菜品模板获取菜品
        List<OrderAndFoodItemComboAssociation> orderAndFoodItemComboAssociations = orderAndFoodItemComboAssociationDao.getOrderAndFoodItemComboAssociationByParameter(map);
        if(orderAndFoodItemComboAssociations==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        if(orderAndFoodItemComboAssociations.size()==0)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        for(OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation:orderAndFoodItemComboAssociations){
            OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociationtemp=new OrderAndFoodItemComboAssociation();
            orderAndFoodItemComboAssociationtemp.setComboTemplateCount(orderAndFoodItemComboAssociation.getComboTemplateCount());
            orderAndFoodItemComboAssociationtemp.setCombo(orderAndFoodItemComboAssociation.getCombo());
            orderAndFoodItemComboAssociationtemp.setFoodItemCount(orderAndFoodItemComboAssociation.getFoodItemCount());
            orderAndFoodItemComboAssociationtemp.setFoodItem(orderAndFoodItemComboAssociation.getFoodItem());
            orderAndFoodItemComboAssociationDao.insert(orderAndFoodItemComboAssociationtemp);
        }
        return ordertemp.getOrderId();
    }
}
