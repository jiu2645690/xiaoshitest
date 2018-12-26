package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.Combo;
import com.xiaoshi.order.service.ComboAndfFoodItemAssociationService;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.ComboTemplateService;
import com.xiaoshi.order.service.FoodItemService;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ComboServiceTest {

    @Autowired
    private ComboAndfFoodItemAssociationService comboAndfFoodItemAssociationService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private ComboTemplateService comboTemplateService;

    @Test
    public void get() {
        assertNotNull(comboService.get(1L));
    }

    @Test
    public void insertCombo() {
        Combo combo = new Combo();
        combo.setComboTemplate(comboTemplateService.get(1L));
        comboService.insertCombo(combo);
        TestCase.assertTrue(comboService.get(combo.getComboId()).getComboId().equals(combo.getComboId()));
    }

    @Test
    public void updateCombo() {
        Combo combo  = comboService.get(1L);
        combo.setComboTemplate(comboTemplateService.get(1L));
        comboService.updateCombo(combo);
        TestCase.assertTrue(comboService.get(combo.getComboId()).getComboTemplate().getComboTemplateId().equals(1L));
    }
}