package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.FoodItemAndTemplateAssociationDao;
import com.xiaoshi.order.pojo.entity.FoodItemAndTemplateAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.FoodItemAndTemplateAssociationService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *  FoodItemAndTemplateAssociationServiceImpl
 **/
@Service
@Slf4j
public class FoodItemAndTemplateAssociationServiceImpl implements FoodItemAndTemplateAssociationService {

    @Autowired
    private FoodItemAndTemplateAssociationDao foodItemAndTemplateAssociationDao;

    /**
     * 通过id获取 FoodItemAndTemplateAssociation
     */
    @Override
    public FoodItemAndTemplateAssociation get(Long id) {
        return foodItemAndTemplateAssociationDao.select(id);
    }

    @Override
    public List<FoodItemAndTemplateAssociation> get(Map<String, String> map) {
        return foodItemAndTemplateAssociationDao.getFoodItemAndTemplateAssociationByParameter(map);
    }

    /**
     * 获取 FoodItemAndTemplateAssociation列表
     */
    @Override
    public List<FoodItemAndTemplateAssociation> getFoodItemAndTemplateAssociationList() {
        return foodItemAndTemplateAssociationDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<FoodItemAndTemplateAssociation> list = foodItemAndTemplateAssociationDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 获取 FoodItemAndTemplateAssociation列表
     */
    @Override
    public List<FoodItemAndTemplateAssociation> getFoodItemAndTemplateAssociationByParameter(Map map) {
        return foodItemAndTemplateAssociationDao.getFoodItemAndTemplateAssociationByParameter(map);
    }

    /**
     * 新增 FoodItemAndTemplateAssociation
     */
    @Override
    public void insertFoodItemAndTemplateAssociation(FoodItemAndTemplateAssociation foodItemAndTemplateAssociation) {
        foodItemAndTemplateAssociationDao.insert(foodItemAndTemplateAssociation);
    }

}
