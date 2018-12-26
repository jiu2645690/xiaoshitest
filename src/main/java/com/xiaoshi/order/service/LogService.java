package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.Log;

public interface LogService {
    /**
     * 新增日志
     */
    void insert(Log log);
    /**
     * 根绝 id 获取 错误日子
     */
    Log select(Long id);
}
