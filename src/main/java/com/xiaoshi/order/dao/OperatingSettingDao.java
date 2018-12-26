package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.OperatingSetting;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface OperatingSettingDao {
    /**
     * 通过id获取OperatingSetting
     */
    OperatingSetting select(Long id);
    /**
     * 新增OperatingSetting
     */
    void insert(OperatingSetting operatingSetting);
    /**
     * 获取OperatingSetting列表
     */
    List<OperatingSetting> selectList();
    /**
     *批量新增 OperatingSetting
     */
    void insertBatch(List<OperatingSetting> operatingSettings);
    /**
     * 更新OperatingSetting
     */
    void updateOperatingSetting(OperatingSetting operatingSetting);
    /**
     * 通过参数获取OperatingSetting列表
     */
    List<OperatingSetting> getOperatingSettingByParameter(Map map);
}
