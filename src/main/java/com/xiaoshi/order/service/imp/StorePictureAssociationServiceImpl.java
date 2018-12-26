package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.StorePictureAssociationDao;
import com.xiaoshi.order.pojo.entity.StorePictureAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.StorePictureAssociationService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * StorePictureAssociationServiceImpl
 **/
@Service
@Slf4j
public class StorePictureAssociationServiceImpl implements StorePictureAssociationService {

    @Autowired
    private StorePictureAssociationDao storePictureAssociationDao;

    /**
     * 获取 StorePictureAssociation
     */
    @Override
    public List<StorePictureAssociation> get(Map<String,String> map) {
        return storePictureAssociationDao.select(map);
    }

    /**
     * 获取 StorePictureAssociation列表
     */
    @Override
    public List<StorePictureAssociation> getStorePictureAssociationList() {
        return  storePictureAssociationDao.selectList();
    }

    /**
     *  分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<StorePictureAssociation> list = storePictureAssociationDao.selectList();
        return PageUtil.setPage(list, page);
    }
}
