package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.OrderAndFoodItemComboAssociationDao;
import com.xiaoshi.order.pojo.entity.OrderAndFoodItemComboAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.OrderAndFoodItemComboAssociationService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  OrderAndFoodItemComboAssociationServiceImpl
 **/
@Service
@Slf4j
public class OrderAndFoodItemComboAssociationServiceImpl implements OrderAndFoodItemComboAssociationService {

    @Autowired
    private OrderAndFoodItemComboAssociationDao orderAndFoodItemComboAssociationDao;

    /**
     * 通过id获得OrderAndFoodItemComboAssociation
     */
    @Override
    public OrderAndFoodItemComboAssociation get(Long id) {
        return orderAndFoodItemComboAssociationDao.select(id);
    }

    /**
     * 获得OrderAndFoodItemComboAssociation列表
     */
    @Override
    public List<OrderAndFoodItemComboAssociation> getOrderAndFoodItemComboAssociationList() {
        return orderAndFoodItemComboAssociationDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<OrderAndFoodItemComboAssociation> list = orderAndFoodItemComboAssociationDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 OrderAndFoodItemComboAssociation
     */
    @Override
    public void insertOrderAndFoodItemComboAssociation(OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation) {
        orderAndFoodItemComboAssociationDao.insert(orderAndFoodItemComboAssociation);
    }

    /**
     * 更新OrderAndFoodItemComboAssociation
     */
    @Override
    public void updateOrderAndFoodItemComboAssociation(OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation){
        orderAndFoodItemComboAssociationDao.updateOrderAndFoodItemComboAssociation(orderAndFoodItemComboAssociation);
    }
}
