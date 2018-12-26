package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.StoreAuth;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;

public interface StoreAuthService {
    StoreAuth get(Long id);

    List<StoreAuth> getStoreAuthList();

    PageModel search();

    void insertStoreAuth(StoreAuth StoreAuth);

    StoreAuth getStoreByParameter(Long id);

    void updateStoreAuth(StoreAuth storeAuth);
}
