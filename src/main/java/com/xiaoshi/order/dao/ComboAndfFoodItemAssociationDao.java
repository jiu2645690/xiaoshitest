package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.ComboAndfFoodItemAssociation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ComboAndfFoodItemAssociationDao {
    /**
     * 通过id获取ComboAndfFoodItemAssociation
     */
    ComboAndfFoodItemAssociation select(Long id);
    /**
     * 新增ComboAndfFoodItemAssociation
     */
    void insert(ComboAndfFoodItemAssociation comboAndfFoodItemAssociation);
    /**
     * 获取ComboAndfFoodItemAssociation列表
     */
    List<ComboAndfFoodItemAssociation> selectList();
    /**
     *批量新增 ComboAndfFoodItemAssociation
     */
    void insertBatch(List<ComboAndfFoodItemAssociation> comboAndfFoodItemAssociations);
    /**
     * 更新CustomerAuth
     */
    void updateComboAndfFoodItemAssociation(ComboAndfFoodItemAssociation comboAndfFoodItemAssociation);
    /**
     * 获取ComboAndfFoodItemAssociation
     */
    List<ComboAndfFoodItemAssociation> getComboAndfFoodItemAssociationByParameter(Map map);
}
