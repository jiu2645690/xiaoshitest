package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Log;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao {
    /**
     * 新增日志
     */
    void insert(Log log);
    /**
     * 根绝 id 获取 错误日子
     */
    Log select(Long id);
}
