package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Transcation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface TranscationDao {
    /**
     * 通过id获取Transcation
     */
    Transcation select(Long id);
    /**
     * 新增Transcation
     */
    void insert(Transcation transcation);
    /**
     * 获取Transcation列表
     */
    List<Transcation> selectList();
    /**
     *批量新增 Transcation
     */
    void insertBatch(List<Transcation> transcations);
    /**
     * 通过传入参数查询是否存在对应Transcation
     */
    List<Transcation> getTranscationByParameter(Map map);
    /**
     * 更新Transcation
     */
    void updateTranscation(Transcation transcation);
}
