package com.xiaoshi.order.service.imp;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.dao.*;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.FoodItemCart;
import com.xiaoshi.order.pojo.dto.FoodItemCount;
import com.xiaoshi.order.pojo.dto.ReturnFoodItem;
import com.xiaoshi.order.pojo.dto.FoodItemOrCombo;
import com.xiaoshi.order.pojo.entity.*;
import com.xiaoshi.order.pojo.form.CartForm;
import com.xiaoshi.order.pojo.form.ComboForm;
import com.xiaoshi.order.pojo.form.FoodItemForm;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.service.ShoppingCartService;
import com.xiaoshi.order.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *ShoppingCartServiceImpl
*/
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private ComboDao comboDao;
    @Autowired
    private ComboAndfFoodItemAssociationDao comboAndfFoodItemAssociationDao;
    @Autowired
    private ShoppingCartLineItemDao shoppingCartLineItemDao;
    @Autowired
    private ComboTemplateDao comboTemplateDao;
    @Autowired
    private FoodItemDao foodItemDao;
    @Autowired
    private StoreDao storeDao;

    /**
     * 获取 ShoppingCart
     */
    @Override
    public ShoppingCart get(Long id) {
        return shoppingCartDao.select(id);
    }

    /**
     * 获取ShoppingCart列表
     */
    @Override
    public List<ShoppingCart> getShoppingCartList() {

        return shoppingCartDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<ShoppingCart> list = shoppingCartDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 通过customerId获取 ShoppingCart
     */
    @Override
    public ShoppingCart getShoppingCartByCustomerId(Long customerId) {
        return shoppingCartDao.getShoppingCartByCustomerId(customerId);
    }

    /**
     * 新增购物车
     */
    @Override
    public void insertShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartDao.insert(shoppingCart);
    }

    /**
     * 将套餐添加到购物车
     */
    @Override
    public String comboIntoShoppingCart(ComboForm comboForm) {
        ComboTemplate comboTemplate = comboTemplateDao.select(comboForm.getComboTemplateId());
        if (comboTemplate == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        //获取customer
        Customer customer = TokenUtil.getTokenModel(comboForm.getToken()).getCustomer();
        //获取customer的shoppingCart
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByCustomerId(customer.getCustomerId());
        if (shoppingCart == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        //获取store
        Store store = storeDao.select(comboForm.getStoreId());
        if (store == null)
            throw new OrderException(OrderCode.STORE_NOT_EXISTS);
        //生成combo
        Combo combo = new Combo();
        combo.setComboTemplate(comboTemplate);
        comboDao.insert(combo);
        //生成comboandfooditemassociation
        ComboAndfFoodItemAssociation comboAndfFoodItemAssociation = new ComboAndfFoodItemAssociation();
        if (comboForm.getFoodItemIdList().size() == 0)
            throw new OrderException(OrderCode.FOOD_NOT_EXISTS);
        comboAndfFoodItemAssociation.setCombo(combo);
        comboAndfFoodItemAssociation.setIsDeleted(false);
//        List<Long> aaaa=new ArrayList<Long>();
//        aaaa.add(1L);
//        aaaa.add(2L);
        List<FoodItemCount> foodItemCounts = getFoodItemCount(comboForm.getFoodItemIdList());
        if (foodItemCounts.size() == 0)
            throw new OrderException(OrderCode.FOOD_NOT_EXISTS);
        for (int i = 0; i < foodItemCounts.size(); i++) {
            comboAndfFoodItemAssociation.setFoodItemCount(foodItemCounts.get(i).getCount());
            comboAndfFoodItemAssociation.setFoodItem(foodItemCounts.get(i).getFoodItem());
            comboAndfFoodItemAssociationDao.insert(comboAndfFoodItemAssociation);
        }
        //生成comboandfooditemassociation
        ShoppingCartLineItem shoppingCartLineItem = new ShoppingCartLineItem();
        shoppingCartLineItem.setCombo(combo);
        shoppingCartLineItem.setComboCount((long) comboForm.getCount());
        shoppingCartLineItem.setCreatedAt(new Date());
        shoppingCartLineItem.setDeliveryDate(comboForm.getDeliveryDate());
        shoppingCartLineItem.setDeliveryTime(comboForm.getDeliveryTime());
        shoppingCartLineItem.setState(0);
        shoppingCartLineItem.setStore(store);
        shoppingCartLineItem.setUpdatedAt(new Date());
        shoppingCartLineItem.setShoppingCart(shoppingCart);
        shoppingCartLineItemDao.insert(shoppingCartLineItem);
        return "comboId:" + combo.getComboId() + ",shoppingCartLineItemId:" + shoppingCartLineItem.getShoppingCartLineItemId();
    }

    /**
     * 将菜品添加到购物车
     */
    @Override
    public String foodItemIntoShoppingCart(FoodItemForm foodItemForm) {
        //获取customer
        Customer customer = TokenUtil.getTokenModel(foodItemForm.getToken()).getCustomer();
        //获取customer的shoppingCart
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByCustomerId(customer.getCustomerId());
        if (shoppingCart == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        //获取store
        Store store = storeDao.select(foodItemForm.getStoreId());
        if (store == null)
            throw new OrderException(OrderCode.STORE_NOT_EXISTS);
        FoodItem foodItem = foodItemDao.select(foodItemForm.getFoodItemId());
        if (foodItem == null)
            throw new OrderException(OrderCode.FOOD_NOT_EXISTS);
        //生成comboandfooditemassociation
        ShoppingCartLineItem shoppingCartLineItem = new ShoppingCartLineItem();
        shoppingCartLineItem.setFoodItem(foodItem);
        shoppingCartLineItem.setFoodItemCount(foodItemForm.getCount());
        shoppingCartLineItem.setCreatedAt(new Date());
        shoppingCartLineItem.setDeliveryDate(foodItemForm.getDeliveryDate());
        shoppingCartLineItem.setDeliveryTime(foodItemForm.getDeliveryTime());
        shoppingCartLineItem.setState(0);
        shoppingCartLineItem.setStore(store);
        shoppingCartLineItem.setUpdatedAt(new Date());
        shoppingCartLineItem.setShoppingCart(shoppingCart);
        shoppingCartLineItemDao.insert(shoppingCartLineItem);
        return "shoppingCartLineItemId:" + shoppingCartLineItem.getShoppingCartLineItemId();
    }

    /**
     * 变更数量
     */
    @Override
    public void updateShoppingCart(CartForm cartForm) {
        //获取customer
        Customer customer = TokenUtil.getTokenModel(cartForm.getToken()).getCustomer();
        //获取customer的shoppingCart
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByCustomerId(customer.getCustomerId());
        if (shoppingCart == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        ShoppingCartLineItem shoppingCartLineItem = shoppingCartLineItemDao.select(cartForm.getShoppingCartLineItemId());
        if (shoppingCart == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        if (cartForm.getFoodItemCount() != null)
            shoppingCartLineItem.setFoodItemCount(cartForm.getFoodItemCount());
        if (cartForm.getComboCount() != null)
            shoppingCartLineItem.setComboCount((long) cartForm.getComboCount());
        shoppingCartLineItemDao.updateShoppingCartLineItem(shoppingCartLineItem);
    }

    /**
     * 购物车统计 顾客基本信息首页 （用于 “我” 这个页面 购物车数量的统计）
     */
    @Override
    public Integer getShoppingCart(String token) {
        //获取customer
        Customer customer = TokenUtil.getTokenModel(token).getCustomer();
        //获取customer的shoppingCart
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByCustomerId(customer.getCustomerId());
        if (shoppingCart == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map = new HashMap();
        map.put("shoppingCartId", shoppingCart.getShoppingCartId());
        map.put("state", 0);
        List<ShoppingCartLineItem> shoppingCartLineItems = shoppingCartLineItemDao.getShoppingCartLineItemByParameter(map);
        if (shoppingCartLineItems == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        return shoppingCartLineItems.size();
    }

    /**
     * 获取shoppingCartLineItem
     */
    @Override
    public List<FoodItemCart> getShoppingCartList(String token) {
        List<FoodItemCart> foodItemCarts = new ArrayList<FoodItemCart>();
        //获取customer
        Customer customer = TokenUtil.getTokenModel(token).getCustomer();
        //获取customer的shoppingCart
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByCustomerId(customer.getCustomerId());
        if (shoppingCart == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        //获取ShoppingCartLineItem分组按照商家、送餐时间、送餐时段
        List<ShoppingCartLineItem> shoppingCartLineItems = shoppingCartLineItemDao.selectShoppingCartLineItemGroup(shoppingCart.getShoppingCartId());
        if (shoppingCartLineItems == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        if (shoppingCartLineItems.size() == 0)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        //循环分组中的数据
        for (ShoppingCartLineItem shoppingCartLineItem : shoppingCartLineItems) {
            Map map = new HashMap();
            map.put("shoppingCartId", shoppingCart.getShoppingCartId());
            map.put("deliveryTime", shoppingCartLineItem.getDeliveryTime());
            map.put("deliveryDate", shoppingCartLineItem.getDeliveryDate());
            map.put("storeId", shoppingCartLineItem.getStore().getStoreId());
            //获取分组中的数据
            List<ShoppingCartLineItem> shoppingCartLineItemTemp = shoppingCartLineItemDao.getShoppingCartLineItemByParameter(map);
            List<FoodItemOrCombo> FooditemOrCombos = new ArrayList<FoodItemOrCombo>();
            FoodItemCart foodItemCart = new FoodItemCart();
            foodItemCart.setStoreId(shoppingCartLineItem.getStore().getStoreId());
            foodItemCart.setDeliveryDate(shoppingCartLineItem.getDeliveryDate());
            foodItemCart.setDeliveryTime(shoppingCartLineItem.getDeliveryTime());
            for (int i = 0; i < shoppingCartLineItemTemp.size(); i++) {
                FoodItemOrCombo foodItemOrCombo = new FoodItemOrCombo();
                if (shoppingCartLineItem.getCombo() != null) {
                    foodItemOrCombo.setComboId(shoppingCartLineItem.getCombo().getComboId());
                    foodItemOrCombo.setComboCount(shoppingCartLineItem.getComboCount().intValue());
                    if (shoppingCartLineItem.getCombo().getComboTemplate() != null)
                        foodItemOrCombo.setComboName(shoppingCartLineItem.getCombo().getComboTemplate().getComboTemplateName());
                    if (shoppingCartLineItem.getCombo().getComboTemplate() != null)
                        if (shoppingCartLineItem.getCombo().getComboTemplate().getPicture() != null)
                            foodItemOrCombo.setComboPictureUrl(shoppingCartLineItem.getCombo().getComboTemplate().getPicture().getPictureUrl());
                }
                if (shoppingCartLineItem.getFoodItem() != null) {
                    foodItemOrCombo.setFoodItemId(shoppingCartLineItem.getFoodItem().getFoodItemId());
                    foodItemOrCombo.setFoodItemCount(shoppingCartLineItem.getFoodItemCount());
                    foodItemOrCombo.setFoodItemName(shoppingCartLineItem.getFoodItem().getName());
                    foodItemOrCombo.setFoodItemprice(shoppingCartLineItem.getFoodItem().getPrice());
                    if (shoppingCartLineItem.getFoodItem().getPicture() != null)
                        foodItemOrCombo.setFoodItemPictureUrl(shoppingCartLineItem.getFoodItem().getPicture().getPictureUrl());
                }
                FooditemOrCombos.add(foodItemOrCombo);
            }
            foodItemCart.setFoodItemOrComboList(FooditemOrCombos);
            foodItemCarts.add(foodItemCart);
        }
        return foodItemCarts;
    }

    /**
     * 删除shoppingCartLineItem
     */
    @Override
    public Boolean deleteShoppingCart(String token, Long id) {
        //获取customer
        Customer customer = TokenUtil.getTokenModel(token).getCustomer();
        //获取customer的shoppingCart
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByCustomerId(customer.getCustomerId());
        if (shoppingCart == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map = new HashMap();
        map.put("shoppingCartId", shoppingCart.getShoppingCartId());
        map.put("shoppingCartLineItemId", id);
        shoppingCartLineItemDao.deleteShoppingCartLineItemByParameter(map);
        return true;
    }

    @Override
    public List<ReturnFoodItem> getComboFoodItem(Long id) {
        //返回前台运营的菜品列表
        List<ReturnFoodItem> list = new ArrayList<ReturnFoodItem>();
        Map map = new HashMap();
        map.put("comboId", id);
        //通过菜品模板获取菜品
        List<ComboAndfFoodItemAssociation> comboAndfFoodItemAssociations = comboAndfFoodItemAssociationDao.getComboAndfFoodItemAssociationByParameter(map);
        if (comboAndfFoodItemAssociations == null) {
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        }
        if (comboAndfFoodItemAssociations.size() == 0) {
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        }
        for (ComboAndfFoodItemAssociation comboAndfFoodItemAssociation : comboAndfFoodItemAssociations) {
            if (comboAndfFoodItemAssociation.getFoodItem() == null) continue;
            ReturnFoodItem returnFoodItem = new ReturnFoodItem();
            returnFoodItem.setDailySpecialPrice(comboAndfFoodItemAssociation.getFoodItem().getDailySpecialPrice());
            returnFoodItem.setFoodItemId(comboAndfFoodItemAssociation.getFoodItem().getFoodItemId());
            returnFoodItem.setName(comboAndfFoodItemAssociation.getFoodItem().getName());
            if (comboAndfFoodItemAssociation.getFoodItem().getPicture() != null)
                returnFoodItem.setPictureUrl(comboAndfFoodItemAssociation.getFoodItem().getPicture().getPictureUrl());
            returnFoodItem.setPrice(comboAndfFoodItemAssociation.getFoodItem().getPrice());
            returnFoodItem.setRemainingCount(comboAndfFoodItemAssociation.getFoodItem().getRemainingCount());
            returnFoodItem.setDailySpecialPrice(comboAndfFoodItemAssociation.getFoodItem().getDailySpecialPrice());
            returnFoodItem.setIsFavorite(false);
            list.add(returnFoodItem);
        }
        return list;
    }

    /**
     * 获取FoodItem和数量
     */
    public List<FoodItemCount> getFoodItemCount(List<Long> foodItemIdList) {
        List<FoodItemCount> foodItemCountList = new ArrayList<FoodItemCount>();
        for (int i = 0; i < foodItemIdList.size(); i++) {
            FoodItem foodItem = foodItemDao.select(foodItemIdList.get(i));
            if (foodItem == null)
                throw new OrderException(OrderCode.FOOD_NOT_EXISTS);
            if (foodItemCountList.size() == 0) {
                FoodItemCount foodItemCount = new FoodItemCount();
                foodItemCount.setFoodItem(foodItem);
                foodItemCount.setCount(1);
                foodItemCountList.add(foodItemCount);
            } else {
                Boolean flag = true;
                for (int j = 0; j < foodItemCountList.size(); j++) {
                    //如果有相同的菜品将对应的菜品数量增加1
                    if (foodItemCountList.get(j).getFoodItem().getFoodItemId() == foodItemIdList.get(i)) {
                        foodItemCountList.get(j).setCount(foodItemCountList.get(j).getCount() + 1);
                        flag = false;
                    }
                }
                if (flag) {
                    FoodItemCount foodItemCount = new FoodItemCount();
                    foodItemCount.setFoodItem(foodItem);
                    foodItemCount.setCount(1);
                    foodItemCountList.add(foodItemCount);
                }
            }
        }
        return foodItemCountList;
    }

}
