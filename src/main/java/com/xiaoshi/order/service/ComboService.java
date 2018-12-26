package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.Combo;
import com.xiaoshi.order.pojo.form.FoodItemForm;
import com.xiaoshi.order.pojo.form.ComboForm;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;

public interface ComboService {
    Combo get(Long id);

    List<Combo> getComboList();

    PageModel search();

    void insertCombo(Combo combo);

    void updateCombo(Combo combo);

}
