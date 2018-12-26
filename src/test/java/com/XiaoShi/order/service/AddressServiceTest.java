package com.XiaoShi.order.service;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.dao.AddressDao;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.ReturnAddress;
import com.xiaoshi.order.pojo.entity.Address;
import com.xiaoshi.order.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void get() {
        assertNotNull(addressService.get(1L));
    }

    @Test
    public void getReturnAddressOrderException()
    {
        thrown.expect(OrderException.class);
        thrown.expectMessage(OrderCode.INFO_NOT_EXISTS.getMessage());
        addressService.getReturnAddress(-1L);
    }

    @Test
    public void getReturnAddress()
    {
        addressService.getReturnAddress(1L);
    }

    @Test
    public void getReturnAddressListOrderException() {
        AddressDao addressDao = Mockito.mock(AddressDao.class);
        addressService.setAddressDao(addressDao);
        List<Address> list = new ArrayList<Address>();
        Mockito.when(addressDao.selectList()).thenReturn(list);
        thrown.expect(OrderException.class);
        thrown.expectMessage(OrderCode.INFO_NOT_EXISTS.getMessage());
        addressService.getReturnAddressList();
    }

    @Test
    public void getReturnAddressList()
    {
        assertNotNull(addressService.getReturnAddressList());
        ;
    }
    @Test
    public void search() {
        assertNotNull(addressService.search(1,10).getList());
    }

}