package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.TranscationDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.pojo.entity.Transcation;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TranscationDaoTest {

    @Autowired
    private TranscationDao transcationDao;
    @Autowired
    private StoreDao storeDao;

    @Test
    public void select() {
        Transcation transcation = transcationDao.select(1L);
        assertNotNull(transcation);
    }

    @Test
    public void insert() {
        Transcation transcation = new Transcation();
        transcation.setCreatedAt(new Date());
        transcation.setStore(storeDao.select(1L));
        transcationDao.insert(transcation);
        TestCase.assertTrue(transcationDao.select(transcation.getTranscationId()).getTranscationId().equals(transcation.getTranscationId()));
    }
}