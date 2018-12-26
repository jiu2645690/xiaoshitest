package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.ReviewPictureAssociationDao;
import com.xiaoshi.order.pojo.entity.ReviewPictureAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.ReviewPictureAssociationService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *  ReviewPictureAssociationServiceImpl
 **/
@Service
@Slf4j
public class ReviewpictureassociationServiceImpl implements ReviewPictureAssociationService {

    @Autowired
    private ReviewPictureAssociationDao ReviewPictureAssociationDao;

    /**
     * 通过id获取 ReviewPictureAssociation
     */
    @Override
    public ReviewPictureAssociation get(Long id) {
        return ReviewPictureAssociationDao.select(id);
    }

    /**
     * 获取 ReviewPictureAssociation列表
     */
    @Override
    public List<ReviewPictureAssociation> getReviewPictureAssociationList() {
        return ReviewPictureAssociationDao.selectList();
    }

    /**
     * 分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<ReviewPictureAssociation> list = ReviewPictureAssociationDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 ReviewPictureAssociation
     */
    @Override
    public void insertReviewPictureAssociation(ReviewPictureAssociation ReviewPictureAssociation) {
        ReviewPictureAssociationDao.insert(ReviewPictureAssociation);
    }

    /**
     * 更新ReviewPictureAssociation
     */
    @Override
    public void updateReviewPictureAssociation(ReviewPictureAssociation ReviewPictureAssociation){
        ReviewPictureAssociationDao.updateReviewPictureAssociation(ReviewPictureAssociation);
    }

    @Override
    public List<ReviewPictureAssociation> getReviewPictureAssociationByParameter(Map map) {
        return ReviewPictureAssociationDao.getReviewPictureAssociationByParameter(map);
    }
}
