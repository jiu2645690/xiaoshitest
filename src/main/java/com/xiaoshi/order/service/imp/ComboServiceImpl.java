package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.dao.*;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.FoodItemCount;
import com.xiaoshi.order.pojo.entity.*;
import com.xiaoshi.order.pojo.form.FoodItemForm;
import com.xiaoshi.order.pojo.form.ComboForm;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.util.PageUtil;
import com.xiaoshi.order.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  ComboServiceImpl
 */
@Service
@Slf4j
public class ComboServiceImpl implements ComboService {

    @Autowired
    private ComboDao comboDao;
    @Autowired
    private ComboAndfFoodItemAssociationDao comboAndfFoodItemAssociationDao;
    @Autowired
    private ShoppingCartLineItemDao shoppingCartLineItemDao;
    @Autowired
    private ComboTemplateDao comboTemplateDao;
    @Autowired
    private FoodItemDao foodItemDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public Combo get(Long id) {
        return comboDao.select(id);
    }

    @Override
    public List<Combo> getComboList() {
        return comboDao.selectList();
    }

    @Override
    /**
     * 分页
     */
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<Combo> list = comboDao.selectList();
        return PageUtil.setPage(list, page);
    }

    @Override
    /**
     * 新增 Combo
     */
    public void insertCombo(Combo combo) {
        comboDao.insert(combo);
    }

    /**
     * 更新Combo
     */
    @Override
    public void updateCombo(Combo combo){
        comboDao.updateCombo(combo);
    }

}
