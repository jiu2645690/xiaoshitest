package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.FoodItemAndTemplateAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface FoodItemAndTemplateAssociationService {
    FoodItemAndTemplateAssociation get(Long id);

    List<FoodItemAndTemplateAssociation> get(Map<String, String> map);

    List<FoodItemAndTemplateAssociation> getFoodItemAndTemplateAssociationList();

    void insertFoodItemAndTemplateAssociation(FoodItemAndTemplateAssociation foodItemAndTemplateAssociation);

    PageModel search();

    List<FoodItemAndTemplateAssociation> getFoodItemAndTemplateAssociationByParameter(Map map);
}
