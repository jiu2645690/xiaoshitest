package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.StorePictureAssociation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface StorePictureAssociationDao {
    /**
     * 通过id获取 StorePictureAssociation
     */
    List<StorePictureAssociation> select(Map<String, String> map);
    /**
     * 新增 StorePictureAssociation
     */
    void insert(StorePictureAssociation storePictureAssociation);
    /**
     *  获取StorePictureAssociation列表
     */
    List<StorePictureAssociation> selectList();

}
