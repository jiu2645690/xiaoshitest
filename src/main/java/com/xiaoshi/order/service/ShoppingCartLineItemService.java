package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.ShoppingCartLineItem;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface ShoppingCartLineItemService {
    ShoppingCartLineItem get(Long id);

    List<ShoppingCartLineItem> getShoppingCartLineItemList();

    PageModel search();

    void insertShoppingCartLineItem(ShoppingCartLineItem shoppingCartLineItem);

    void updateShoppingCartLineItem(ShoppingCartLineItem shoppingCartLineItem);

    List<ShoppingCartLineItem> getShoppingCartLineItemByParameter(Map map);
}
