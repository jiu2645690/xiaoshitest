package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.StoreAuth;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoreAuthDao {
    /**
     * 通过id获取StoreAuth
     */
    StoreAuth select(Long id);
    /**
     * 新增StoreAuth
     */
    void insert(StoreAuth storeAuth);
    /**
     * 获取StoreAuth列表
     */
    List<StoreAuth> selectList();
    /**
     *批量新增 StoreAuth
     */
    void insertBatch(List<StoreAuth> storeAuths);
    /**
     * 通过传入参数查询是否存在对应StoreAuth
     */
    StoreAuth getStoreByParameter(Long id);
    /**
     * 更新StoreAuth
     */
    void updateStoreAuth(StoreAuth storeAuth);
}
