package com.xiaoshi.order.service.imp;

import com.xiaoshi.order.pojo.dto.ReturnAddress;
import com.xiaoshi.order.pojo.entity.Address;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.AddressService;
import com.xiaoshi.order.util.PageUtil;
import com.xiaoshi.order.dao.AddressDao;
import com.xiaoshi.order.exception.OrderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static com.xiaoshi.order.constant.OrderCode.INFO_NOT_EXISTS;

/**
 * AddressServiceImpl
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    /**
     * 用于单元测试中 mock AddressDao对象
     */
    @Override
    public void setAddressDao(AddressDao addressDaoForTest) {
        addressDao = addressDaoForTest;
    }

    /**
     * 根据id 获取信息
     */
    @Override
    public Address get(Long id) {
        return addressDao.select(id);
    }

    /**
     * 通过id获取 Address
     */
    @Override
    public ReturnAddress getReturnAddress(Long id) {
        ReturnAddress returnAddress = new ReturnAddress();
        Address address = addressDao.select(id);
        if (address == null) {
            throw new OrderException(INFO_NOT_EXISTS);
        } else {
            returnAddress.setAddressId(address.getAddressId());
            returnAddress.setAddressName(address.getAddressName());
            returnAddress.setZipCode(address.getZipCode());
        }
        return returnAddress;
    }

    @Override
    /**
     * 获取 Address列表
     */
    public List<ReturnAddress> getReturnAddressList() {
        List<Address> list = addressDao.selectList();
        List<ReturnAddress> returnAddresslist = new ArrayList<ReturnAddress>();
        if (list.isEmpty()) {
            throw new OrderException(INFO_NOT_EXISTS);
        } else {
            for (Address address : list) {
                ReturnAddress returnAddress = new ReturnAddress();
                returnAddress.setAddressId(address.getAddressId());
                returnAddress.setAddressName(address.getAddressName());
                returnAddress.setZipCode(address.getZipCode());
                returnAddresslist.add(returnAddress);
            }
        }
        return returnAddresslist;
    }

    @Override
    /**
     *  分页
     */
    public PageModel search(Integer pageNum, Integer pageSize) {
        List<Address> list = addressDao.selectList();
        return PageUtil.setPage(list, PageUtil.setPage(pageNum, pageSize));
    }
}
