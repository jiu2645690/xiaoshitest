package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.OrderAndFoodItemComboAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;

public interface OrderAndFoodItemComboAssociationService {
    OrderAndFoodItemComboAssociation get(Long id);

    List<OrderAndFoodItemComboAssociation> getOrderAndFoodItemComboAssociationList();

    PageModel search();

    void insertOrderAndFoodItemComboAssociation(OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation);

    void updateOrderAndFoodItemComboAssociation(OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation);
}
