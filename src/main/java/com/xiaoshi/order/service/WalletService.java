package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.Wallet;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface WalletService {
    Wallet get(Long id);

    List<Wallet> getWalletList();

    PageModel search();

    void insertWallet(Wallet wallet);

    List<Wallet> getWalletByParameter(Map map);

    void updateWallet(Wallet wallet);
}
