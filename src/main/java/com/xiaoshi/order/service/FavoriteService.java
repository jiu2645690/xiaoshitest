package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.dto.FavoriteCombos;
import com.xiaoshi.order.pojo.dto.FavoriteFoodItems;
import com.xiaoshi.order.pojo.dto.FavoriteStores;
import com.xiaoshi.order.pojo.entity.Favorite;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface FavoriteService {
    Favorite get(Long id);

    List<Favorite> getFavoriteList();

    PageModel search();

    void insertFavorite(Favorite favorite);

    void deleteFavorite(Map map);

    List<FavoriteStores> getFavoriteStores(String token, Integer pageNum, Integer pageSize);

    List<FavoriteFoodItems> getFavoriteFoodItems(String token, Integer pageNum, Integer pageSize);

    List<FavoriteCombos> getFavoriteCombos(String token, Integer pageNum, Integer pageSize);

    void updateFavorite(Favorite favorite);

    Integer getFavoriteStatistics(String token);
}
