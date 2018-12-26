package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.SalesSettingDao;
import com.xiaoshi.order.pojo.entity.SalesSetting;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.SalesSettingService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  SalesSettingServiceImpl
 **/
@Service
@Slf4j
public class SalesSettingServiceImpl implements SalesSettingService {

    @Autowired
    private SalesSettingDao salesSettingDao;

    /**
     * 通过id获取 SalesSetting
     */
    @Override
    public SalesSetting get(Long id) {
        return salesSettingDao.select(id);
    }

    /**
     * 获取 SalesSetting列表
     */
    @Override
    public List<SalesSetting> getSalesSettingList() {
        return salesSettingDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<SalesSetting> list = salesSettingDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 SalesSetting
     */
    @Override
    public void insertSalesSetting(SalesSetting salesSetting) {
        salesSettingDao.insert(salesSetting);
    }

    /**
     * 更新SalesSetting
     */
    @Override
    public void updateSalesSetting(SalesSetting salesSetting){
        salesSettingDao.updateSalesSetting(salesSetting);
    }
}
