package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.StoreAddressAssociationDao;
import com.xiaoshi.order.pojo.entity.StoreAddressAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.StoreAddressAssociationService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *  StoreAddressAssociationServiceImpl
 **/
@Service
@Slf4j
public class StoreAddressAssociationServiceImpl implements StoreAddressAssociationService {

    @Autowired
    private StoreAddressAssociationDao storeAddressAssociationDao;

    /**
     * 通过id获取StoreAddressAssociation
     */
    @Override
    public StoreAddressAssociation get(Long id) {
        return storeAddressAssociationDao.select(id);
    }

    /**
     * 获取StoreAddressAssociation列表
     */
    @Override
    public List<StoreAddressAssociation> getStoreAddressAssociationList() {
        return storeAddressAssociationDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<StoreAddressAssociation> list = storeAddressAssociationDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 StoreAddressAssociation
     */
    @Override
    public void insertStoreAddressAssociation(StoreAddressAssociation storeAddressAssociation) {
        storeAddressAssociationDao.insert(storeAddressAssociation);
    }

    /**
     * 更新StoreAddressAssociation
     */
    @Override
    public void updateStoreAddressAssociation(StoreAddressAssociation storeAddressAssociation){
        storeAddressAssociationDao.updateStoreAddressAssociation(storeAddressAssociation);
    }

    /**
     * 通过参数获取StoreAddressAssociation列表
     */
    @Override
    public List<StoreAddressAssociation> getStoreAddressAssociationByParameter(Map map) {
        return storeAddressAssociationDao.getStoreAddressAssociationByParameter(map);
    }
}
