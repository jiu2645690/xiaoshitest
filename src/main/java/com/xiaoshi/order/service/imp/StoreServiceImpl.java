package com.xiaoshi.order.service.imp;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.dao.*;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.*;
import com.xiaoshi.order.pojo.entity.*;
import com.xiaoshi.order.pojo.form.StoreForm;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.ComboTemplateService;
import com.xiaoshi.order.service.FoodItemAndTemplateAssociationService;
import com.xiaoshi.order.service.StoreService;
import com.xiaoshi.order.util.PageUtil;
import com.xiaoshi.order.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StoreServiceImpl
 */
@Service
@Slf4j
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDao storeDao;
    @Autowired
    private SalesSettingDao salesSettingDao;
    @Autowired
    private StoreAddressAssociationDao storeAddressAssociationDao;
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private FavoriteDao favoriteDao;
    @Autowired
    private ComboTemplateService comboTemplateService;
    @Autowired
    private FoodItemAndTemplateAssociationService foodItemAndTemplateAssociationService;

    /**
     * 通过id获取 Store
     */
    @Override
    public Store get(Long id) {
        return storeDao.select(id);
    }

    @Override
    public MeStore getStoreById(String token) {
        Store store = TokenUtil.getTokenModel(token).getStore();
        if (store == null) throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Store storetemp = storeDao.select(store.getStoreId());
        MeStore meStore =new MeStore();
        meStore.setBalance(storetemp.getBalance());
        meStore.setFirstName(storetemp.getFirstName());
        meStore.setLastName(storetemp.getLastName());
        if(storetemp.isSex()){
            meStore.setSex("男");
        }else{
            meStore.setSex("女");
        }
        meStore.setNickName(storetemp.getNickName());
        Map map = new HashMap();
        map.put("storeId", store.getStoreId());
        map.put("storeIsDeleted", 0);
        map.put("customerIsDeleted", 0);
        map.put("zhangPeriod", 0);
        map.put("state", 1);
        List<Order> orders=orderDao.getOrderByParameter(map);
        BigDecimal temp=new BigDecimal(0);
        for(Order order:orders){
            temp=temp.add(order.getTotalAmount());
        }
        meStore.setZhangPeriodStatistics(temp);
        return meStore;
    }

    /**
     * 获取Store列表
     */
    @Override
    public List<Store> getStoreList() {
        return storeDao.selectList();
    }

    /**
     * 获取Store店铺特惠区
     */
    @Override
    public PageModel getDishComboList(Long storeId, String token, Integer pageNum, Integer pageSize) {
        List<DishCombo> list = new ArrayList<DishCombo>();
        Map map = new HashMap();
        map.put("storeId", storeId);
        map.put("customerId", TokenUtil.getTokenModel(token).getCustomer().getCustomerId());
        List<Favorite> favorite = favoriteDao.getFavoriteByParameter(map);
        List<ComboTemplate> comboTemplateList = comboTemplateService.getComboTemplateByParameter(map);
        if (comboTemplateList == null) {
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        }
        if (comboTemplateList.size() == 0) {
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        }
        for (ComboTemplate comboTemplate : comboTemplateList) {
            DishCombo dishCombo = new DishCombo();
            dishCombo.setComboTemplateDiscount(comboTemplate.getComboTemplateDiscount());
            dishCombo.setComboId(comboTemplate.getComboTemplateId());
            dishCombo.setName(comboTemplate.getComboTemplateName());
            dishCombo.setRemainingCount(comboTemplate.getComboTemplateRemainingCount());
            dishCombo.setPictureUrl(comboTemplate.getPicture().getPictureUrl());
            if (favorite != null) {
                dishCombo.setIsFavorite(true);
            } else {
                dishCombo.setIsFavorite(false);
            }
            list.add(dishCombo);
        }

        return PageUtil.setPage(list, PageUtil.setPage(pageNum, pageSize));
    }

    /**
     * 获取当前日期运营的菜品
     */
    @Override
    public PageModel getFoodItemTemplateList(Long currentFoodItemTemplateId, String token, Integer pageNum, Integer pageSize) {
        //返回前台运营的菜品列表
        List<ReturnFoodItem> list = new ArrayList<ReturnFoodItem>();
        Map map = new HashMap();
        map.put("foodItemTemplateId", currentFoodItemTemplateId);
        if (token != null)
            map.put("customerId", TokenUtil.getTokenModel(token).getCustomer().getCustomerId());
        //通过菜品模板获取菜品
        List<FoodItemAndTemplateAssociation> foodItemAndTemplateAssociations = foodItemAndTemplateAssociationService.get(map);
        if (foodItemAndTemplateAssociations == null) {
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        }
        if (foodItemAndTemplateAssociations.size() == 0) {
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        }
        for (FoodItemAndTemplateAssociation foodItemAndTemplateAssociation : foodItemAndTemplateAssociations) {
            if (foodItemAndTemplateAssociation.getFoodItem() == null) continue;
            ReturnFoodItem returnFoodItem = new ReturnFoodItem();
            returnFoodItem.setDailySpecialPrice(foodItemAndTemplateAssociation.getFoodItem().getDailySpecialPrice());
            returnFoodItem.setFoodItemId(foodItemAndTemplateAssociation.getFoodItem().getFoodItemId());
            returnFoodItem.setName(foodItemAndTemplateAssociation.getFoodItem().getName());
            if (foodItemAndTemplateAssociation.getFoodItem().getPicture() != null)
                returnFoodItem.setPictureUrl(foodItemAndTemplateAssociation.getFoodItem().getPicture().getPictureUrl());
            returnFoodItem.setPrice(foodItemAndTemplateAssociation.getFoodItem().getPrice());
            returnFoodItem.setRemainingCount(foodItemAndTemplateAssociation.getFoodItem().getRemainingCount());
            returnFoodItem.setDailySpecialPrice(foodItemAndTemplateAssociation.getFoodItem().getDailySpecialPrice());
            map.put("foodItemId", foodItemAndTemplateAssociation.getFoodItem().getFoodItemId());
            List<Favorite> favorite = favoriteDao.getFavoriteByParameter(map);
            if (favorite != null) {
                returnFoodItem.setIsFavorite(true);
            } else {
                returnFoodItem.setIsFavorite(false);
            }
            list.add(returnFoodItem);
        }
        return PageUtil.setPage(list, PageUtil.setPage(pageNum, pageSize));
    }

    /**
     * 分页
     */
    @Override
    public PageModel search(StoreForm storeForm) {
        List<StoreList> list = new ArrayList<StoreList>();
        List<Store> listStore = storeDao.selectStoreList(storeForm);
        if (listStore == null) {
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        }
        for (Store store : listStore) {
            StoreList storeList = new StoreList();
            storeList.setAvgScore(store.getAverageScore());
            storeList.setStoreId(store.getStoreId());
            storeList.setStoreName(store.getStoreName());
            storeList.setPictureUrl(salesSettingDao.select(store.getStoreId()).getPicture().getPictureUrl());
            //判断是否收藏,统计评价数量,送餐地址
            Map map = new HashMap();
            map.put("storeId", store.getStoreId());
            map.put("addressId", storeForm.getAddressId());
            //判断送餐地址的加进传到前台的List
            List<StoreAddressAssociation> storeAddressAssociation = storeAddressAssociationDao.getStoreAddressAssociationByParameter(map);
            //为空表示没有查到对应的地址，结束本次循环
            if (storeAddressAssociation == null) continue;
            //判断是否收藏和统计评价数量
            if (storeForm.getToken() != null) {
                map.put("customerId", TokenUtil.getTokenModel(storeForm.getToken()).getCustomer().getCustomerId());
                List<Favorite> favorite = favoriteDao.getFavoriteByParameter(map);
                List reviewList = reviewDao.getReviewByParameter(map);
                if (favorite != null) {
                    storeList.setIsFavorite(true);
                } else {
                    storeList.setIsFavorite(false);
                }
                if (reviewList != null) {
                    storeList.setReviewCount(((long) (reviewList.size())));
                } else {
                    storeList.setReviewCount(0L);
                }
            } else {
                storeList.setIsFavorite(false);
                //只有storeId没有传入用户表示商店被所有评价的数量
                List reviewList = reviewDao.getReviewByParameter(map);
                if (reviewList != null) {
                    storeList.setReviewCount(((long) (reviewList.size())));
                } else {
                    storeList.setReviewCount(0L);
                }
            }
            //送餐时间段
            List<Integer> mealTimeList = new ArrayList<>();
            if (salesSettingDao.select(store.getStoreId()).getIsHaveDinner()) {
                mealTimeList.add(1);
            }
            if (salesSettingDao.select(store.getStoreId()).getIsHaveLunch()) {
                mealTimeList.add(0);
            }
            storeList.setMealTimeList(mealTimeList);
            list.add(storeList);
        }

        if (list == null) {
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        }
        return PageUtil.setPage(list, PageUtil.setPage(storeForm.getPageNum(), storeForm.getPageSize()));
    }

    /**
     * 获取Store列表通过storeId和用户token
     */
    @Override
    public StoreList search(Long storeId, String token) {
        Store store = storeDao.select(storeId);
        StoreList storeList = new StoreList();
        storeList.setAvgScore(store.getAverageScore());
        storeList.setStoreId(store.getStoreId());
        storeList.setStoreName(store.getStoreName());
        storeList.setIntroduction(store.getIntroduction());
        storeList.setPictureUrl(salesSettingDao.select(store.getStoreId()).getPicture().getPictureUrl());
        //判断是否收藏,统计评价数量,送餐地址
        Map map = new HashMap();
        map.put("storeId", store.getStoreId());
        //判断是否收藏和统计评价数量
        if (token != null) {
            if (!token.equals("")) {
                map.put("customerId", TokenUtil.getTokenModel(token).getCustomer().getCustomerId());
                List<Favorite> favorite = favoriteDao.getFavoriteByParameter(map);
                List reviewList = reviewDao.getReviewByParameter(map);
                if (favorite != null) {
                    storeList.setIsFavorite(true);
                } else {
                    storeList.setIsFavorite(false);
                }
                if (reviewList != null) {
                    storeList.setReviewCount(((long) (reviewList.size())));
                } else {
                    storeList.setReviewCount(0L);
                }
            } else {
                storeList.setIsFavorite(false);
                //只有storeId没有传入用户表示商店被所有评价的数量
                List reviewList = reviewDao.getReviewByParameter(map);
                if (reviewList != null) {
                    storeList.setReviewCount(((long) (reviewList.size())));
                } else {
                    storeList.setReviewCount(0L);
                }
            }
        } else {
            storeList.setIsFavorite(false);
            //只有storeId没有传入用户表示商店被所有评价的数量
            List reviewList = reviewDao.getReviewByParameter(map);
            if (reviewList != null) {
                storeList.setReviewCount(((long) (reviewList.size())));
            } else {
                storeList.setReviewCount(0L);
            }
        }
        //送餐时间段
        List<Integer> mealTimeList = new ArrayList<>();
        if (salesSettingDao.select(store.getStoreId()).getIsHaveDinner()) {
            mealTimeList.add(1);
        }
        if (salesSettingDao.select(store.getStoreId()).getIsHaveLunch()) {
            mealTimeList.add(0);
        }
        storeList.setMealTimeList(mealTimeList);
        return storeList;
    }

    /**
     * 新增Store
     */
    @Override
    public void insertStore(Store Store) {
        storeDao.insert(Store);
    }

    /**
     * 更新Store
     */
    @Override
    public Boolean updateStore(Store store) {
        try {
            storeDao.updateStore(store);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 获取Store记录数
     */
    @Override
    public Integer getStoreCount(Map<String, String> map) {
        return storeDao.getStoreCount(map);
    }

    /**
     * 根据参数判断是否存在对应数据
     */
    @Override
    public void isStoreExist(String key, String val) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, val);
        if (getStoreCount(map) > 0) {
            throw new OrderException(OrderCode.LOGIN_NOT_SUCCESS);
        }
    }

    /**
     * 根据参数判断登录用户是否存在
     */
    public void isLoginStoreExist(String key, String val) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, val);
        if (!(getStoreCount(map) > 0)) {
            throw new OrderException(OrderCode.USER_NOT_EXISTS);
        }
    }

    /**
     * 根据参数是否存在
     */
    public Boolean isStoreVerifyExist(String key, String val) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, val);
        if (!(getStoreCount(map) > 0)) {
            return false;
        }
        return true;
    }

    /**
     * 根据参数判断登录Wechat用户是否存在
     */
    @Override
    public void isWechatLoginStoreExist(String key, String val) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, val);
        if (!(getStoreCount(map) > 0)) {

        }
    }

    /**
     * 根据参数判断是否存在对应Store
     */
    @Override
    public Store getStoreByParameter(String key, String val) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, val);
        return storeDao.getStoreByParameter(map);
    }
}
