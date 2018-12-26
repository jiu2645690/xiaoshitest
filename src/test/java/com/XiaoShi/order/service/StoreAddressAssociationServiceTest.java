package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.StoreAddressAssociation;
import com.xiaoshi.order.service.OrderService;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.StoreAddressAssociationService;
import com.xiaoshi.order.service.AddressService;
import com.xiaoshi.order.service.StoreService;
import com.xiaoshi.order.service.PictureService;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StoreAddressAssociationServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private StoreAddressAssociationService storeAddressAssociationService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PictureService pictureService;

    @Test
    public void get() {
        assertNotNull(storeAddressAssociationService.get(1L));
    }

    @Test
    public void insertStoreAddressAssociation() {
        StoreAddressAssociation storeAddressAssociation = new StoreAddressAssociation();
        storeAddressAssociation.setStore(storeService.get(1L));
        storeAddressAssociation.setAddress(addressService.get(1L));
        storeAddressAssociationService.insertStoreAddressAssociation(storeAddressAssociation);
        TestCase.assertTrue(storeAddressAssociationService.get(storeAddressAssociation.getStore().getStoreId()).getStore().getStoreId().equals(storeAddressAssociation.getStore().getStoreId()));
    }

    @Test
    public void updateStoreAddressAssociation() {
        StoreAddressAssociation storeAddressAssociation = storeAddressAssociationService.get(1L);
        storeAddressAssociation.setAddress(addressService.get(2L));
        storeAddressAssociationService.updateStoreAddressAssociation(storeAddressAssociation);
        TestCase.assertTrue(storeAddressAssociationService.get(1L).getAddress().getAddressId().equals(2L));
    }
}