package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.FoodItemPictureAssociation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface FoodItemPictureAssociationDao {
    /**
     * 通过参数获取 FoodItemPictureAssociation
     */
    List<FoodItemPictureAssociation> getFoodItemPictureAssociationByParameter(Map<String, String> map);
    /**
     * 新增 FoodItemPictureAssociation
     */
    void insert(FoodItemPictureAssociation foodItemPictureAssociation);
    /**
     *  获取FoodItemPictureAssociation列表
     */
    List<FoodItemPictureAssociation> selectList();

}
