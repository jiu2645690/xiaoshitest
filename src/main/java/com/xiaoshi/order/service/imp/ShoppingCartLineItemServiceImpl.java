package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.ShoppingCartLineItemDao;
import com.xiaoshi.order.pojo.entity.ShoppingCartLineItem;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.ShoppingCartLineItemService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *  ShoppingCartLineItemServiceImpl
 **/
@Service
@Slf4j
public class ShoppingCartLineItemServiceImpl implements ShoppingCartLineItemService {

    @Autowired
    private ShoppingCartLineItemDao shoppingCartLineItemDao;

    /**
     * 通过id获取 ShoppingCartLineItem
     */
    @Override
    public ShoppingCartLineItem get(Long id) {
        return shoppingCartLineItemDao.select(id);
    }

    /**
     * 获取 ShoppingCartLineItem列表
     */
    @Override
    public List<ShoppingCartLineItem> getShoppingCartLineItemList() {
        return shoppingCartLineItemDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<ShoppingCartLineItem> list = shoppingCartLineItemDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 ShoppingCartLineItem
     */
    @Override
    public void insertShoppingCartLineItem(ShoppingCartLineItem shoppingCartLineItem) {
        shoppingCartLineItemDao.insert(shoppingCartLineItem);
    }

    /**
     * 更新ShoppingCartLineItem
     */
    @Override
    public void updateShoppingCartLineItem(ShoppingCartLineItem shoppingCartLineItem) {
        shoppingCartLineItemDao.updateShoppingCartLineItem(shoppingCartLineItem);
    }

    @Override
    public List<ShoppingCartLineItem> getShoppingCartLineItemByParameter(Map map) {
        return shoppingCartLineItemDao.getShoppingCartLineItemByParameter(map);
    }
}
