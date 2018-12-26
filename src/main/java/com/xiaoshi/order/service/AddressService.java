package com.xiaoshi.order.service;

import com.xiaoshi.order.dao.AddressDao;
import com.xiaoshi.order.pojo.dto.ReturnAddress;
import com.xiaoshi.order.pojo.entity.Address;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;

public interface AddressService {

    //根据id 获取信息
    Address get(Long id);

    //用于单元测试中 mock AddressDao对象
    void setAddressDao(AddressDao addressDaoForTest);

    //获取(系统设置的)送餐地址
    ReturnAddress getReturnAddress(Long id);

    //获取送餐地址列表
    List<ReturnAddress> getReturnAddressList();

    //通过分页获取送餐地址列表
    PageModel search(Integer pageNum, Integer pageSize);

}
