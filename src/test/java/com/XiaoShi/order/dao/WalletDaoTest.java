package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.dao.WalletDao;
import com.xiaoshi.order.pojo.entity.Wallet;
import com.xiaoshi.order.pojo.form.TransactionForm;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WalletDaoTest {

    @Autowired
    private WalletDao walletDao;
    @Autowired
    private StoreDao storeDao;

    @Test
    public void select() {
        Wallet wallet = walletDao.select(1L);
        assertNotNull(wallet);
    }

    @Test
    public void insert() {
        Wallet wallet = new Wallet();
        wallet.setCreatedAt(new Date());
        wallet.setPeopleId(2L);
        walletDao.insert(wallet);
        TestCase.assertTrue(walletDao.select(wallet.getWalletId()).getWalletId().equals(wallet.getWalletId()));
    }

}