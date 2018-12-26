package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.OperatingSettingDao;
import com.xiaoshi.order.pojo.entity.OperatingSetting;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.OperatingSettingService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *  OperatingSettingServiceImpl
 **/
@Service
@Slf4j
public class OperatingSettingServiceImpl implements OperatingSettingService {

    @Autowired
    private OperatingSettingDao OperatingSettingDao;

    /**
     * 通过id获取 OperatingSetting
     */
    @Override
    public OperatingSetting get(Long id) {
        return OperatingSettingDao.select(id);
    }

    /**
     * 获取 OperatingSetting列表
     */
    @Override
    public List<OperatingSetting> getOperatingSettingList() {
        return OperatingSettingDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<OperatingSetting> list = OperatingSettingDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 OperatingSetting
     */
    @Override
    public void insertOperatingSetting(OperatingSetting OperatingSetting) {
        OperatingSettingDao.insert(OperatingSetting);
    }

    /**
     * 更新OperatingSetting
     */
    @Override
    public void updateOperatingSetting(OperatingSetting OperatingSetting){
        OperatingSettingDao.updateOperatingSetting(OperatingSetting);
    }

    @Override
    public List<OperatingSetting> getOperatingSettingByParameter(Map map) {
        return OperatingSettingDao.getOperatingSettingByParameter(map);
    }
}
