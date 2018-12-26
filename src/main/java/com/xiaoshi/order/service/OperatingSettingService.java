package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.OperatingSetting;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface OperatingSettingService {
    OperatingSetting get(Long id);

    List<OperatingSetting> getOperatingSettingList();

    PageModel search();

    void insertOperatingSetting(OperatingSetting OperatingSetting);

    void updateOperatingSetting(OperatingSetting OperatingSetting);

    List<OperatingSetting> getOperatingSettingByParameter(Map map);
}
