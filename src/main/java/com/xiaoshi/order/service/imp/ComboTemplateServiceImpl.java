package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.ComboTemplateDao;
import com.xiaoshi.order.pojo.entity.ComboTemplate;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.ComboTemplateService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *  ComboTemplateServiceImpl
 **/
@Service
@Slf4j
public class ComboTemplateServiceImpl implements ComboTemplateService {

    @Autowired
    private ComboTemplateDao comboTemplateDao;

    /**
     * 通过id获取 ComboTemplate
     */
    @Override
    public ComboTemplate get(Long id) {
        return comboTemplateDao.select(id);
    }

    /**
     * 获取 ComboTemplate列表
     */
    @Override
    public List<ComboTemplate> getComboTemplateList() {
        return comboTemplateDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<ComboTemplate> list = comboTemplateDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 ComboTemplate
     */
    @Override
    public void insertComboTemplate(ComboTemplate comboTemplate) {
        comboTemplateDao.insert(comboTemplate);
    }

    /**
     * 更新ComboTemplate
     */
    @Override
    public void updateComboTemplate(ComboTemplate comboTemplate){
        comboTemplateDao.updateComboTemplate(comboTemplate);
    }

    /**
     * 通过参数获取ComboTemplate列表
     */
    @Override
    public List<ComboTemplate> getComboTemplateByParameter(Map map) {
        return comboTemplateDao.getComboTemplateByParameter(map);
    }
}
