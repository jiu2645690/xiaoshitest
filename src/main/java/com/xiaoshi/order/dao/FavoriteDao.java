package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Favorite;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface FavoriteDao {
    /**
     * 通过id获取Favorite
     */
    Favorite select(Long id);
    /**
     * 新增Favorite
     */
    void insert(Favorite favorite);
     /**
     * 删除Favorite
     */
    void deleteFavorite(Map map);
    /**
     * 获取Favorite列表
     */
    List<Favorite> selectList();
    /**
     *批量新增 Favorite
     */
    void insertBatch(List<Favorite> favorites);
    /**
     * 更新CustomerAuth
     */
    void updateFavorite(Favorite favorite);
    /**
     * 通过参数获取Favorite
     */
    List<Favorite> getFavoriteByParameter(Map map);
}
