package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.dto.ReturnFoodItem;
import com.xiaoshi.order.pojo.entity.FoodItem;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;

public interface FoodItemService {
    FoodItem get(Long id);

    List<FoodItem> getFoodItemList();

    PageModel search();

    void insertFoodItem(FoodItem foodItem);

    void updateFoodItem(FoodItem foodItem);

    ReturnFoodItem getFoodItem(Long id);

    List getFoodItemPicture(Long fooditemId);
}
