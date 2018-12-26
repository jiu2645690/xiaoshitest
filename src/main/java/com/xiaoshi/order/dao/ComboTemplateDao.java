package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.ComboTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ComboTemplateDao {
    /**
     * 通过id获取ComboTemplate
     */
    ComboTemplate select(Long id);
    /**
     * 新增ComboTemplate
     */
    void insert(ComboTemplate comboTemplate);
    /**
     * 获取ComboTemplate列表
     */
    List<ComboTemplate> selectList();
    /**
     *批量新增 ComboTemplate
     */
    void insertBatch(List<ComboTemplate> comboTemplates);
    /**
     * 更新CustomerAuth
     */
    void updateComboTemplate(ComboTemplate comboTemplate);
    /**
     * 通过参数获取ComboTemplate列表
     */
    List<ComboTemplate> getComboTemplateByParameter(Map map);
}
