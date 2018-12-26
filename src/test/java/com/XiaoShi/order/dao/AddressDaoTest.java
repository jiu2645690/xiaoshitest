package com.XiaoShi.order.dao;

import com.xiaoshi.order.pojo.entity.Address;
import com.xiaoshi.order.dao.AddressDao;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AddressDaoTest {

    @Autowired
    private AddressDao addressDao;

    @Test
    public void select() {
        Address address = addressDao.select(1L);
        assertNotNull(address);
    }

    @Test
    public void selectList() {
        List<Address> list= addressDao.selectList();
        assertNotNull(list);
    }

    @Test
    public void insert() {
        Address address = new Address();
        address.setAddressName("测试地址3");
        address.setCity("city");
        address.setState("state");
        address.setZipCode("123456");
        addressDao.insert(address);
        TestCase.assertTrue(addressDao.select(address.getAddressId()).getAddressId().equals(address.getAddressId()));
    }

    @Test
    public void insertBatch() {
        List<Address> list = Stream.iterate(0, i -> i + 1).limit(3).map(i -> {
            Address address = new Address();
            address.setZipCode("zipCode" + i);
            address.setAddressName("address" + i);
            address.setState("state" + i);
            address.setIsDeleted(i % 2 == 0);
            address.setCity("city" + i);
            return address;
        }).collect(Collectors.toList());
        addressDao.insertBatch(list);
    }

}