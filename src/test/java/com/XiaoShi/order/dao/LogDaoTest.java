package com.XiaoShi.order.dao;

import com.xiaoshi.order.constant.LogCode;
import com.xiaoshi.order.dao.LogDao;
import com.xiaoshi.order.pojo.entity.Log;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogDaoTest {

    @Autowired
    private LogDao logDao;
    @Test
    public void insert() {
        Log log=new Log();
        log.setLogContent("测试插入");
        log.setLogType(LogCode.SEVER_ERROR.getCode());
        logDao.insert(log);
        TestCase.assertTrue(logDao.select(log.getLogId()).getLogId().equals(log.getLogId()));
    }
    @Test
    public  void select() {
        Log log = logDao.select(1L);
        assertNotNull(log);
    }
}