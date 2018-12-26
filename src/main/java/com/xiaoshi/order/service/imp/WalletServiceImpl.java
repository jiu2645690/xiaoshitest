package com.xiaoshi.order.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.dao.WalletDao;
import com.xiaoshi.order.pojo.entity.Wallet;
import com.xiaoshi.order.pojo.model.PageModel;
import com.xiaoshi.order.service.WalletService;
import com.xiaoshi.order.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *WalletServiceImpl
*/
@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletDao walletDao;

    /**
     * 通过id获取 Wallet
     */
    @Override
    public Wallet get(Long id) {
        return walletDao.select(id);
    }

    /**
     * 获取 Wallet列表
     */
    @Override
    public List<Wallet> getWalletList() {
        return walletDao.selectList();
    }

    /**
     *  分页
     */
    @Override
    public PageModel search() {
        Page page = PageHelper.startPage(1, 20, true);
        List<Wallet> list = walletDao.selectList();
        return PageUtil.setPage(list, page);
    }

    /**
     * 新增 Wallet
     */
    @Override
    public void insertWallet(Wallet wallet) {
        walletDao.insert(wallet);
    }

    /**
     * 通过Store获取Wallet
     */
    @Override
    public List<Wallet> getWalletByParameter(Map map) {
        return walletDao.getWalletByParameter(map);
    }
    /**
     * 更新Wallet
     */
    @Override
    public void updateWallet(Wallet wallet) {
        walletDao.updateWallet(wallet);
    }
}
