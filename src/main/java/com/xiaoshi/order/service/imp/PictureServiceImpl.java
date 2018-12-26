package com.xiaoshi.order.service.imp;

import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.pojo.entity.Picture;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.PictureService;
import com.xiaoshi.order.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  PictureServiceImpl
*/

@Service
@Slf4j
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    /**
     * 获取 Picture
     */
    @Override
    public Picture get(Long id) {
        return pictureDao.select(id);
    }

    /**
     * 获取Picture列表
     */
    @Override
    public List<Picture> getPictureList() {
        return pictureDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<Picture> list = pictureDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增picture
     */
    @Override
    public void insertPicture(Picture picture) {
        pictureDao.insert(picture);
    }
}
