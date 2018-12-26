package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.ShoppingCartLineItem;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ShoppingCartLineItemDao {
    /**
     * 通过id获取ShoppingCartLineItem
     */
    ShoppingCartLineItem select(Long id);
    /**
     * 新增ShoppingCartLineItem
     */
    void insert(ShoppingCartLineItem shoppingCartLineItem);
    /**
     * 获取ShoppingCartLineItem列表
     */
    List<ShoppingCartLineItem> selectList();
    /**
     *批量新增 ShoppingCartLineItem
     */
    void insertBatch(List<ShoppingCartLineItem> shoppingCartLineItems);
    /**
     * 更新ShoppingCartLineItem
     */
    void updateShoppingCartLineItem(ShoppingCartLineItem shoppingCartLineItem);
    /**
     * 通过参数获取ShoppingCartLineItem列表
     */
    List<ShoppingCartLineItem> getShoppingCartLineItemByParameter(Map map);
   /**
     * 通过参数删除ShoppingCartLineItem列表
     */
   void deleteShoppingCartLineItemByParameter(Map map);
    /**
     * 通过ShoppingCartId参数ShoppingCartLineItem
     */
    List<ShoppingCartLineItem> selectShoppingCartLineItemGroup(Long id);
    /**
     * 统计用户购物车中的 商品数量
     */
    Integer getShoppingCartLineItemCountByCustomerId(Long id);
}
