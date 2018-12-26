package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.AddressDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.dao.StoreAddressAssociationDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.pojo.entity.StoreAddressAssociation;
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
public class StoreAddressAssociationDaoTest {

    @Autowired
    private StoreAddressAssociationDao storeAddressAssociationDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private AddressDao addressDao;

    @Test
    public void select() {
        StoreAddressAssociation storeAddressAssociation = storeAddressAssociationDao.select(1L);
        assertNotNull(storeAddressAssociation);
    }

    @Test
    public void insert() {
        StoreAddressAssociation storeAddressAssociation = new StoreAddressAssociation();
        storeAddressAssociation.setStore(storeDao.select(1L));
        storeAddressAssociation.setAddress(addressDao.select(1L));
        storeAddressAssociationDao.insert(storeAddressAssociation);
        TestCase.assertTrue(storeAddressAssociationDao.select(storeAddressAssociation.getStore().getStoreId()).getStore().getStoreId().equals(1L));
    }

    @Test
    public void update() {
        StoreAddressAssociation storeAddressAssociation = storeAddressAssociationDao.select(1L);
        storeAddressAssociationDao.updateStoreAddressAssociation(storeAddressAssociation);
        TestCase.assertTrue(storeAddressAssociationDao.select(storeAddressAssociation.getStore().getStoreId()).getStore().getStoreId().equals(1L));
    }

}