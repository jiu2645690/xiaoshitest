package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.CustomerAuth;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;

public interface CustomerAuthService {
    CustomerAuth get(Long id);

    List<CustomerAuth> getCustomerAuthList();

    PageModel search();

    void insertCustomerAuth(CustomerAuth customerAuth);

    CustomerAuth getCustomerByParameter(Long id);

    void updateCustomerAuth(CustomerAuth customerAuth);
}
