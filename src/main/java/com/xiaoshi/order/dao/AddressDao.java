package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Address;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressDao {
    /**
     * 通过id获取地址
     */
    Address select(Long id);
    /**
     * 新增地址
     */
    void insert(Address address);
    /**
     * 获取地址列表
     */
    List<Address> selectList();
    /**
     * 批量新增地址
     */
    void insertBatch(List<Address> addresses);
}
