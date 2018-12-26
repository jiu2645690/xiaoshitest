package com.xiaoshi.order.service.imp;

import com.xiaoshi.order.dao.StoreAuthDao;
import com.xiaoshi.order.pojo.entity.StoreAuth;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.StoreAuthService;
import com.xiaoshi.order.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *StoreAuthServiceImpl
*/
@Service
@Slf4j
public class StoreAuthServiceImpl implements StoreAuthService {

    @Autowired
    private StoreAuthDao storeAuthDao;

    /**
     * 通过id获取 StoreAuth
     */
    @Override
    public StoreAuth get(Long id) {
        return storeAuthDao.select(id);
    }

    /**
     * 获取 StoreAuth列表
     */
    @Override
    public List<StoreAuth> getStoreAuthList() {
        return storeAuthDao.selectList();
    }

    /**
     *  分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<StoreAuth> list = storeAuthDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 StoreAuth
     */
    @Override
    public void insertStoreAuth(StoreAuth StoreAuth) {
        storeAuthDao.insert(StoreAuth);
    }

    /**
     * 通过Store获取StoreAuth
     */
    @Override
    public StoreAuth getStoreByParameter(Long id) {
        return storeAuthDao.getStoreByParameter(id);
    }
    /**
     * 更新StoreAuth
     */
    @Override
    public void updateStoreAuth(StoreAuth storeAuth) {
        storeAuthDao.updateStoreAuth(storeAuth);
    }
}
