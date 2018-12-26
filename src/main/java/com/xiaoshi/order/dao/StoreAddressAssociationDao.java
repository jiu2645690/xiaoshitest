package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.StoreAddressAssociation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface StoreAddressAssociationDao {
    /**
     * 通过id获取StoreAddressAssociation
     */
    StoreAddressAssociation select(Long id);
    /**
     * 新增StoreAddressAssociation
     */
    void insert(StoreAddressAssociation storeAddressAssociation);
    /**
     * 获取StoreAddressAssociation列表
     */
    List<StoreAddressAssociation> selectList();
    /**
     *批量新增 StoreAddressAssociation
     */
    void insertBatch(List<StoreAddressAssociation> storeAddressAssociations);
    /**
     * 更新StoreAddressAssociation
     */
    void updateStoreAddressAssociation(StoreAddressAssociation storeAddressAssociation);
    /**
     * 通过参数获取StoreAddressAssociation
     */
    List<StoreAddressAssociation> getStoreAddressAssociationByParameter(Map map);
}
