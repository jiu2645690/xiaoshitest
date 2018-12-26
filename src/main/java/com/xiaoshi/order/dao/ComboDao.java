package com.xiaoshi.order.dao;

import com.xiaoshi.order.pojo.entity.Combo;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComboDao {
    /**
     * 通过id获取Combo
     */
    Combo select(Long id);
    /**
     * 新增Combo
     */
    void insert(Combo combo);
    /**
     * 获取Combo列表
     */
    List<Combo> selectList();
    /**
     *批量新增 Combo
     */
    void insertBatch(List<Combo> combos);
    /**
     * 更新CustomerAuth
     */
    void updateCombo(Combo combo);
}
