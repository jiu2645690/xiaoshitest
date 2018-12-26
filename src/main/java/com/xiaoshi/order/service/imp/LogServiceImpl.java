package com.xiaoshi.order.service.imp;

import com.xiaoshi.order.dao.LogDao;
import com.xiaoshi.order.pojo.entity.Log;
import com.xiaoshi.order.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  LogServiceImpl
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    /**
     * 新增日志
     */
    @Override
    public void insert(Log log) {
        logDao.insert(log);
    }

    /**
     * 获取日志
     */
    @Override
    public Log select(Long id) {
        return logDao.select(id);
    }
}
