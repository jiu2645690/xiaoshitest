package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.Address;
import com.xiaoshi.order.pojo.entity.ComboAndfFoodItemAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface ComboAndfFoodItemAssociationService {
    ComboAndfFoodItemAssociation get(Long id);

    List<ComboAndfFoodItemAssociation> getComboAndfFoodItemAssociationList();

    PageModel search();

    void insertComboAndfFoodItemAssociation(ComboAndfFoodItemAssociation comboAndfFoodItemAssociation);

    void updateComboAndfFoodItemAssociation(ComboAndfFoodItemAssociation comboAndfFoodItemAssociation);

    List<ComboAndfFoodItemAssociation> getComboAndfFoodItemAssociationByParameter(Map map);
}
