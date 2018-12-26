package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.ComboAndfFoodItemAssociationDao;
import com.xiaoshi.order.pojo.entity.ComboAndfFoodItemAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.ComboAndfFoodItemAssociationService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *  ComboAndfFoodItemAssociationServiceImpl
 */
@Service
@Slf4j
public class ComboAndfFoodItemAssociationServiceImpl implements ComboAndfFoodItemAssociationService {

    @Autowired
    private ComboAndfFoodItemAssociationDao comboAndfFoodItemAssociationDao;

    @Override
    public ComboAndfFoodItemAssociation get(Long id) {
        return comboAndfFoodItemAssociationDao.select(id);
    }

    @Override
    public List<ComboAndfFoodItemAssociation> getComboAndfFoodItemAssociationList() {
        return comboAndfFoodItemAssociationDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<ComboAndfFoodItemAssociation> list = comboAndfFoodItemAssociationDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 ComboAndfFoodItemAssociation
     */
    @Override
    public void insertComboAndfFoodItemAssociation(ComboAndfFoodItemAssociation comboAndfFoodItemAssociation) {
        comboAndfFoodItemAssociationDao.insert(comboAndfFoodItemAssociation);
    }

    /**
     * 更新ComboAndfFoodItemAssociation
     */
    @Override
    public void updateComboAndfFoodItemAssociation(ComboAndfFoodItemAssociation comboAndfFoodItemAssociation){
        comboAndfFoodItemAssociationDao.updateComboAndfFoodItemAssociation(comboAndfFoodItemAssociation);
    }

    @Override
    public List<ComboAndfFoodItemAssociation> getComboAndfFoodItemAssociationByParameter(Map map) {
        return comboAndfFoodItemAssociationDao.getComboAndfFoodItemAssociationByParameter(map);
    }
}
