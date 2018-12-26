package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.TranscationDao;
import com.xiaoshi.order.pojo.entity.Transcation;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.TranscationService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 *TranscationServiceImpl
*/
@Service
@Slf4j
public class TranscationServiceImpl implements TranscationService {

    @Autowired
    private TranscationDao transcationDao;

    /**
     * 通过id获取 Transcation
     */
    @Override
    public Transcation get(Long id) {
        return transcationDao.select(id);
    }

    /**
     * 获取 Transcation列表
     */
    @Override
    public List<Transcation> getTranscationList() {
        return transcationDao.selectList();
    }

    /**
     *  分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<Transcation> list = transcationDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 Transcation
     */
    @Override
    public void insertTranscation(Transcation transcation) {
        transcationDao.insert(transcation);
    }

    /**
     * 通过参数获取Transcation
     */
    @Override
    public List<Transcation> getTranscationByParameter(Map map) {
        return transcationDao.getTranscationByParameter(map);
    }
    /**
     * 更新Transcation
     */
    @Override
    public void updateTranscation(Transcation transcation) {
        transcationDao.updateTranscation(transcation);
    }
}
