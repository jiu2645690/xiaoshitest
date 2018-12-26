package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.SalesSetting;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SalesSettingDao {
    /**
     * 通过id获取SalesSetting
     */
    SalesSetting select(Long id);
    /**
     * 新增SalesSetting
     */
    void insert(SalesSetting salesSetting);
    /**
     * 获取SalesSetting列表
     */
    List<SalesSetting> selectList();
    /**
     *批量新增 SalesSetting
     */
    void insertBatch(List<SalesSetting> salesSettings);
    /**
     * 更新SalesSetting
     */
    void updateSalesSetting(SalesSetting salesSetting);
}
