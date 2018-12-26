package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Picture;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PictureDao {
    /**
     *  通过id获取 Picture
     */
    Picture select(Long id);
    /**
     *  新增 Picture
     */
    void insert(Picture picture);
    /**
     * 获取Picture列表
     */
    List<Picture> selectList();

}
