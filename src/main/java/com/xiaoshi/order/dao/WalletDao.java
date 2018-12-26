package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Wallet;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface WalletDao {
    /**
     * 通过id获取Wallet
     */
    Wallet select(Long id);
    /**
     * 新增Wallet
     */
    void insert(Wallet wallet);
    /**
     * 获取Wallet列表
     */
    List<Wallet> selectList();
    /**
     *批量新增 Wallet
     */
    void insertBatch(List<Wallet> wallets);
    /**
     * 通过传入参数查询是否存在对应Wallet
     */
    List<Wallet> getWalletByParameter(Map map);
    /**
     * 更新Wallet
     */
    void updateWallet(Wallet wallet);
}
