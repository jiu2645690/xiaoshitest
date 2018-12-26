package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Store;

import com.xiaoshi.order.pojo.form.StoreForm;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface StoreDao {
    /**
     * 通过id获取 Store
     */
    Store select(Long id);
    /**
     * 新增 Store
     */
    void insert(Store store);
     /**
     * 更新 Store
     */
    void updateStore(Store store);
    /**
     * 获取 Store列表
     */
    List<Store> selectList();
    /**
     * 获取 Store列表
     */
    List<Store> selectStoreList(StoreForm storeForm);
    /**
     *  通过传入参数查询是否存在对应数据
     */
    Integer getStoreCount(Map<String, String> map);
    /**
     *  通过传入参数查询是否存在对应Store
     */
    Store getStoreByParameter(Map<String, String> map);

}
