package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.CustomerAuth;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerAuthDao {
    /**
     * 通过id获取CustomerAuth
     */
    CustomerAuth select(Long id);
    /**
     * 新增CustomerAuth
     */
    void insert(CustomerAuth customerAuth);
    /**
     * 获取CustomerAuth列表
     */
    List<CustomerAuth> selectList();
    /**
     * 批量新增 CustomerAuth
     */
    void insertBatch(List<CustomerAuth> customerAuths);
    /**
     *  通过传入参数查询是否存在对应CustomerAuth
     */
    CustomerAuth getCustomerByParameter(Long id);
    /**
     * 更新CustomerAuth
     */
    void updateCustomerAuth(CustomerAuth customerAuth);
}
