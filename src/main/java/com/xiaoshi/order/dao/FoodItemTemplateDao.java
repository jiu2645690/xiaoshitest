package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.FoodItemTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface FoodItemTemplateDao {
    /**
     * 通过id获取FoodItemTemplate
     */
    FoodItemTemplate select(Long id);
    /**
     * 新增FoodItemTemplate
     */
    void insert(FoodItemTemplate foodItemTemplate);
    /**
     * 获取FoodItemTemplate列表
     */
    List<FoodItemTemplate> selectList();
    /**
     *批量新增 FoodItemTemplate
     */
    void insertBatch(List<FoodItemTemplate> foodItemTemplates);
    /**
     * 更新CustomerAuth
     */
    void updateFoodItemTemplate(FoodItemTemplate foodItemTemplate);
    /**
     * 通过参数获取FoodItemTemplate列表
     */
    List<FoodItemTemplate> getFoodItemTemplateByParameter(Map map);
}
