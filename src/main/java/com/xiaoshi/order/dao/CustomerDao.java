package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Customer;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface CustomerDao {
    /**
     * 通过id获取 Customer
     */
    Customer select(Long id);
    /**
     *  新增 Customer
     */
    void insert(Customer cstomer);
    /**
     *  更新 Customer
     */
    void updateCustomer(Customer cstomer);
    /**
     *  获取 Customer列表
     */
    List<Customer> selectList();
    /**
     *  通过传入参数查询是否存在对应数据
     */
    Integer getCustomerCount(Map<String, String> map);
    /**
     * 通过传入参数查询是否存在对应Customer
     */
    Customer getCustomerByParameter(Map<String, String> map);

}
