package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.SalesSetting;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;

public interface SalesSettingService {
    SalesSetting get(Long id);

    List<SalesSetting> getSalesSettingList();

    PageModel search();

    void insertSalesSetting(SalesSetting salesSetting);

    void updateSalesSetting(SalesSetting salesSetting);
}
