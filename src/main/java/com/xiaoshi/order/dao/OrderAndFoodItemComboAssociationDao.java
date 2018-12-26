package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.OrderAndFoodItemComboAssociation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderAndFoodItemComboAssociationDao {
    /**
     * 通过id获取OrderAndFoodItemComboAssociation
     */
    OrderAndFoodItemComboAssociation select(Long id);
    /**
     * 新增OrderAndFoodItemComboAssociation
     */
    void insert(OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation);
    /**
     * 获取OrderAndFoodItemComboAssociation列表
     */
    List<OrderAndFoodItemComboAssociation> selectList();
    /**
     * 批量新增 OrderAndFoodItemComboAssociation
     */
    void insertBatch(List<OrderAndFoodItemComboAssociation> orderAndFoodItemComboAssociations);
    /**
     *  通过传入参数查询是否存在对应OrderAndFoodItemComboAssociation
     */
    List<OrderAndFoodItemComboAssociation> getOrderAndFoodItemComboAssociationByParameter(Map map);
    /**
     * 更新OrderAndFoodItemComboAssociation
     */
    void updateOrderAndFoodItemComboAssociation(OrderAndFoodItemComboAssociation orderAndFoodItemComboAssociation);
}
