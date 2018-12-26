package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.dao.FoodItemDao;
import com.xiaoshi.order.dao.FoodItemPictureAssociationDao;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.ReturnFoodItem;
import com.xiaoshi.order.pojo.entity.FoodItem;
import com.xiaoshi.order.pojo.entity.FoodItemPictureAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.FoodItemService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  FoodItemServiceImpl
 */
@Service
@Slf4j
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemDao foodItemDao;
    @Autowired
    private FoodItemPictureAssociationDao foodItemPictureAssociationDao;

    /**
     * 通过id获取FoodItem
     */
    @Override
    public FoodItem get(Long id) {
        return foodItemDao.select(id);
    }

    /**
     * 获取FoodItem列表
     */
    @Override
    public List<FoodItem> getFoodItemList() {
        return foodItemDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<FoodItem> list = foodItemDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 FoodItem
     */
    @Override
    public void insertFoodItem(FoodItem foodItem) {
        foodItemDao.insert(foodItem);
    }

    /**
     * 更新FoodItem
     */
    @Override
    public void updateFoodItem(FoodItem foodItem){
        foodItemDao.updateFoodItem(foodItem);
    }

    /**
     * 获取FoodItem
     */
    @Override
    public ReturnFoodItem getFoodItem(Long id) {
        FoodItem foodItem = foodItemDao.select(id);
        if (foodItem == null) throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        ReturnFoodItem ReturnFoodItem = new ReturnFoodItem();
        ReturnFoodItem.setName(foodItem.getName());
        ReturnFoodItem.setPrice(foodItem.getPrice());
        ReturnFoodItem.setPrimaryIngredient(foodItem.getPrimaryIngredient());
        ReturnFoodItem.setComplementaryIngredient(foodItem.getComplementaryIngredient());
        ReturnFoodItem.setDescription(foodItem.getDescription());
        if (foodItem.getPicture() != null)
            ReturnFoodItem.setPictureUrl(foodItem.getPicture().getPictureUrl());
        return ReturnFoodItem;
    }

    /**
     * 获取FoodItem图片
     */
    @Override
    public List getFoodItemPicture(Long fooditemId) {
        List list = new ArrayList();
        Map map = new HashMap();
        map.put("fooditemId", fooditemId);
        List<FoodItemPictureAssociation> foodItemPictureAssociations = foodItemPictureAssociationDao.getFoodItemPictureAssociationByParameter(map);
        if (foodItemPictureAssociations == null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        if (foodItemPictureAssociations.size() == 0)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        for (FoodItemPictureAssociation foodItemPictureAssociation : foodItemPictureAssociations) {
            if (foodItemPictureAssociation.getPicture() != null)
                list.add(foodItemPictureAssociation.getPicture().getPictureUrl());
        }
        return list;
    }
}
