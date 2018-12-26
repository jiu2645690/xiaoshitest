package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.FoodItemAndTemplateAssociation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface FoodItemAndTemplateAssociationDao {
    /**
     * 通过id获取FoodItemAndTemplateAssociation
     */
    FoodItemAndTemplateAssociation select(Long id);
    /**
     * 新增FoodItemAndTemplateAssociation
     */
    void insert(FoodItemAndTemplateAssociation foodItemAndTemplateAssociation);
    /**
     * 获取FoodItemAndTemplateAssociation列表
     */
    List<FoodItemAndTemplateAssociation> selectList();
    /**
     *批量新增 FoodItemAndTemplateAssociation
     */
    void insertBatch(List<FoodItemAndTemplateAssociation> foodItemAndTemplateAssociations);
    /**
     * 获取FoodItemAndTemplateAssociation
     */
    List<FoodItemAndTemplateAssociation> getFoodItemAndTemplateAssociationByParameter(Map map);
}
