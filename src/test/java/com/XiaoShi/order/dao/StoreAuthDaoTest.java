package com.XiaoShi.order.dao;

import com.xiaoshi.order.pojo.entity.StoreAuth;
import com.xiaoshi.order.dao.StoreAuthDao;
import com.xiaoshi.order.dao.StoreDao;
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
public class StoreAuthDaoTest {

    @Autowired
    private StoreAuthDao storeAuthDao;
    @Autowired
    private StoreDao storeDao;

    @Test
    public void select() {
        StoreAuth StoreAuth = storeAuthDao.select(1L);
        assertNotNull(StoreAuth);
    }

    @Test
    public void insert() {
        StoreAuth storeAuth = new StoreAuth();
        storeAuth.setAllowLogin("1");
        storeAuth.setCreatedAt(new Date());
        storeAuth.setStore(storeDao.select(1l));
        storeAuth.setStoreSecretkey("ewr344");
        storeAuth.setPassword("rwtertreytryut");
        storeAuthDao.insert(storeAuth);
        TestCase.assertTrue(storeAuthDao.select(storeAuth.getStoreAuthId()).getStoreAuthId().equals(storeAuth.getStoreAuthId()));
    }
}