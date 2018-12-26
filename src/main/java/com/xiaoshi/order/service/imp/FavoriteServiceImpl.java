package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.dao.FavoriteDao;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.FavoriteCombos;
import com.xiaoshi.order.pojo.dto.FavoriteFoodItems;
import com.xiaoshi.order.pojo.dto.FavoriteStores;
import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.pojo.entity.Favorite;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.FavoriteService;
import com.xiaoshi.order.util.PageUtil;
import com.xiaoshi.order.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  FavoriteServiceImpl
 **/
@Service
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteDao favoriteDao;

    /**
     * 通过id获取 Favorite
     */
    @Override
    public Favorite get(Long id) {
        return favoriteDao.select(id);
    }

    /**
     * 获取 Favorite列表
     */
    @Override
    public List<Favorite> getFavoriteList() {
        return favoriteDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<Favorite> list = favoriteDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 Favorite
     */
    @Override
    public void insertFavorite(Favorite favorite) {
        favoriteDao.insert(favorite);
    }

    @Override
    public void deleteFavorite(Map map) {
        favoriteDao.deleteFavorite(map);
    }

    /**
     * 获取收藏店铺列表
     */
    @Override
    public List<FavoriteStores> getFavoriteStores(String token, Integer pageNum, Integer pageSize) {
        Customer customer=TokenUtil.getTokenModel(token).getCustomer();
        List<FavoriteStores> savoriteStorestemp=new ArrayList<FavoriteStores>();
        if(customer==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        map.put("customerId",customer.getCustomerId());
        List<Favorite> favorites=favoriteDao.getFavoriteByParameter(map);
        for(Favorite favorite:favorites){
            if(favorite.getStoreId() != null){
                FavoriteStores favoriteStores=new FavoriteStores();
                favoriteStores.setStoreId(favorite.getStoreId().getStoreId());
                favoriteStores.setStoreName(favorite.getStoreId().getStoreName());
                favoriteStores.setPictureUrl(favorite.getStoreId().getPicture().getPictureUrl());
                savoriteStorestemp.add(favoriteStores);
            }
        }
        return PageUtil.setPage(savoriteStorestemp,PageUtil.setPage(pageNum,pageSize)).getList();
    }

    /**
     * 获取收藏菜品列表
     */
    @Override
    public List<FavoriteFoodItems> getFavoriteFoodItems(String token, Integer pageNum, Integer pageSize) {
        Customer customer=TokenUtil.getTokenModel(token).getCustomer();
        List<FavoriteFoodItems> favoriteFoodItemstemp=new ArrayList<FavoriteFoodItems>();
        if(customer==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        map.put("customerId",customer.getCustomerId());
        List<Favorite> favorites=favoriteDao.getFavoriteByParameter(map);
        for(Favorite favorite:favorites){
            if(favorite.getFoodItemId() != null){
                FavoriteFoodItems favoriteFoodItems=new FavoriteFoodItems();
                favoriteFoodItems.setFoodItemId(favorite.getFoodItemId().getFoodItemId());
                favoriteFoodItems.setName(favorite.getFoodItemId().getName());
                favoriteFoodItems.setPictureUrl(favorite.getFoodItemId().getPicture().getPictureUrl());
                favoriteFoodItemstemp.add(favoriteFoodItems);
            }
        }
        return PageUtil.setPage(favoriteFoodItemstemp,PageUtil.setPage(pageNum,pageSize)).getList();
    }

    /**
     * 获取收藏菜品列表
     */
    @Override
    public List<FavoriteCombos> getFavoriteCombos(String token, Integer pageNum, Integer pageSize) {
        Customer customer=TokenUtil.getTokenModel(token).getCustomer();
        List<FavoriteCombos> favoriteCombostemp=new ArrayList<FavoriteCombos>();
        if(customer==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        map.put("customerId",customer.getCustomerId());
        List<Favorite> favorites=favoriteDao.getFavoriteByParameter(map);
        for(Favorite favorite:favorites){
            if(favorite.getComboTemplateId() != null){
                FavoriteCombos favoriteCombos=new FavoriteCombos();
                favoriteCombos.setComboTemplateId(favorite.getComboTemplateId().getComboTemplateId());
                favoriteCombos.setComboTemplateName(favorite.getComboTemplateId().getComboTemplateName());
                favoriteCombos.setPictureUrl(favorite.getComboTemplateId().getPicture().getPictureUrl());
                favoriteCombostemp.add(favoriteCombos);
            }
        }
        return PageUtil.setPage(favoriteCombostemp,PageUtil.setPage(pageNum,pageSize)).getList();
    }

    /**
     * 更新Favorite
     */
    @Override
    public void updateFavorite(Favorite favorite){
        favoriteDao.updateFavorite(favorite);
    }

    /**
     * 收藏夹统计顾客基本信息首页（用于 我”这个页面收藏夹总数统计）
     */
    @Override
    public Integer getFavoriteStatistics(String token) {
        Customer customer=TokenUtil.getTokenModel(token).getCustomer();
        if(customer==null)
            throw new OrderException(OrderCode.INFO_NOT_EXISTS);
        Map map=new HashMap();
        map.put("customerId",customer.getCustomerId());
        List<Favorite> favorites=favoriteDao.getFavoriteByParameter(map);
        if(favorites==null) return 0;
        return favorites.size();
    }

}
