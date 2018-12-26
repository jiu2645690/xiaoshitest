package com.XiaoShi.order.dao;

import com.xiaoshi.order.pojo.entity.Store;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.pojo.form.StoreForm;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StoreDaoTest {

    @Autowired
    private StoreDao storeDao;
    @Autowired
    private PictureDao pictureDao;

    @Test
    public void select() {
        Store Store = storeDao.select(1L);
        assertNotNull(Store);
    }

    @Test
    public void selectList() {
        StoreForm storeForm =new StoreForm();
        storeForm.setStar(5);
        storeForm.setAverageScore(11.3D);
        storeForm.setAddressId(1L);
        storeForm.setDeliveryTime(0);
        storeForm.setPageNum(1);
        storeForm.setPageSize(10);
        List<Store> Store = storeDao.selectStoreList(storeForm);
        assertNotNull(Store);
    }

    @Test
    public void getStoreCount() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("email", "sdafdsa@sdaf.com");
        assertNotNull(storeDao.getStoreCount(map));
    }

    @Test
    public void insert() {
        Store store = new Store();
        store.setAverageScore(22.1);
        store.setBusinessAddress("test.test");
        store.setStar(5);
        store.setIntroduction("testtesttest");
        store.setStoreName("test");
        store.setBalance(new BigDecimal("4.56"));
        store.setDeleted(false);
        store.setEmail("sdafdsa@sdaf.com");
        store.setEmailIsValidated("1");
        store.setFirstName("z1");
        store.setLastName("z2");
        store.setNickName("zwzw");
        store.setPhoneNumber("12345678909");
        store.setPhoneNumberIsValidated("1");
        store.setSex(false);
        store.setPicture(pictureDao.select(1l));
        store.setWechatOpenid("32431253543");
        store.setWechatOpenidIsValidated("1");
        storeDao.insert(store);
        TestCase.assertTrue(storeDao.select(store.getStoreId()).getStoreId().equals(store.getStoreId()));
    }
}