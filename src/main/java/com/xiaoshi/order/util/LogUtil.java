package com.xiaoshi.order.util;

import com.xiaoshi.order.pojo.entity.Log;
import com.xiaoshi.order.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * Result工具类
 */
@Component
public class LogUtil {
    @Autowired
    private LogService logService;
    private static LogService staticlogService;
    @PostConstruct
    public void init() {
        staticlogService = logService;
    }

    /**
     * 添加错误日志
     */
    public static void setErrorLog(Log log) {
        //  吸入错误日志
        staticlogService.insert(log);
    }
}
