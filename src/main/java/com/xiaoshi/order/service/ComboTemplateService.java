package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.ComboTemplate;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface ComboTemplateService {
    ComboTemplate get(Long id);

    List<ComboTemplate> getComboTemplateList();

    PageModel search();

    void insertComboTemplate(ComboTemplate comboTemplate);

    void updateComboTemplate(ComboTemplate comboTemplate);

    List<ComboTemplate> getComboTemplateByParameter(Map map);
}
