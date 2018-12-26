package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.dto.MeStore;
import com.xiaoshi.order.pojo.dto.StoreList;
import com.xiaoshi.order.pojo.form.StoreForm;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.pojo.entity.Store;
import java.util.List;
import java.util.Map;

public interface StoreService {
    Store get(Long id);

    MeStore getStoreById(String token);

    List<Store> getStoreList();

    PageModel getDishComboList(Long storeId, String token, Integer pageNum, Integer pageSize);

    PageModel getFoodItemTemplateList(Long currentFoodItemTemplateId, String token, Integer pageNum, Integer pageSize);

    PageModel search(StoreForm storeForm);

    StoreList search(Long storeId, String token);

    void insertStore(Store store);

    Boolean updateStore(Store store);

    Integer getStoreCount(Map<String, String> map);

    void isStoreExist(String key, String val);

    Store getStoreByParameter(String key, String val);

    void isLoginStoreExist(String key, String val);

    Boolean isStoreVerifyExist(String key, String val);

    void isWechatLoginStoreExist(String key, String val);
}
