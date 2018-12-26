package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.dto.MeCustomer;
import com.xiaoshi.order.pojo.dto.TransactionStatistics;
import com.xiaoshi.order.pojo.dto.WalletTransaction;
import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.pojo.form.TransactionForm;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    Customer get(Long id);

    MeCustomer getCustomerById(String token);

    List<WalletTransaction>  getwalletTransaction(String token);

    List<WalletTransaction>  getwalletStoreTransaction(TransactionForm transactionForm);

    TransactionStatistics getwalletStoreTransactionStatistics(String token, Integer year, Integer month);

    List<Customer> getCustomerList();

    PageModel search();

    void insertCustomer(Customer customer);

    Boolean updateCustomer(Customer customer);

    Integer getCustomerCount(Map<String, String> map);

    void isCustomerExist(String key, String val);

    Customer getCustomerByParameter(String key, String val);

    void isLoginCustomerExist(String key, String val);

    Boolean isCustomerVerifyExist(String key, String val);

    void isWechatLoginCustomerExist(String key, String val);
}
