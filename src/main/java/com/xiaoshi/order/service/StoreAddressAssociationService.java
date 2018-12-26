package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.StoreAddressAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface StoreAddressAssociationService {
    StoreAddressAssociation get(Long id);

    List<StoreAddressAssociation> getStoreAddressAssociationList();

    PageModel search();

    void insertStoreAddressAssociation(StoreAddressAssociation storeAddressAssociation);

    void updateStoreAddressAssociation(StoreAddressAssociation storeAddressAssociation);

    List<StoreAddressAssociation> getStoreAddressAssociationByParameter(Map map);
}
