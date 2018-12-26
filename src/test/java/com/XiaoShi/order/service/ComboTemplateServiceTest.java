package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.ComboTemplate;
import com.xiaoshi.order.service.ComboAndfFoodItemAssociationService;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.StoreService;
import com.xiaoshi.order.service.PictureService;
import com.xiaoshi.order.service.FoodItemService;
import com.xiaoshi.order.service.ComboTemplateService;
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
public class ComboTemplateServiceTest {

    @Autowired
    private ComboAndfFoodItemAssociationService comboAndfFoodItemAssociationService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private ComboTemplateService comboTemplateService;

    @Test
    public void get() {
        assertNotNull(comboService.get(1L));
    }

    @Test
    public void insertComboTemplate() {
        ComboTemplate comboTemplate = new ComboTemplate();
        comboTemplate.setComboTemplateDiscount(333D);
        comboTemplate.setComboTemplateName("33333");
        comboTemplate.setComboTemplateNumber(345);
        comboTemplate.setComboTemplateRemainingCount(12);
        comboTemplate.setStore(storeService.get(1L));
        comboTemplate.setPicture(pictureService.get(1L));
        comboTemplateService.insertComboTemplate(comboTemplate);
        TestCase.assertTrue(comboTemplateService.get(comboTemplate.getComboTemplateId()).getComboTemplateId().equals(comboTemplate.getComboTemplateId()));
    }

    @Test
    public void updateComboAndfFoodItemAssociation() {
        ComboTemplate comboTemplate   = comboTemplateService.get(1L);
        comboTemplate.setComboTemplateNumber(444444);
        comboTemplateService.updateComboTemplate(comboTemplate);
        TestCase.assertTrue(comboTemplateService.get(1L).getComboTemplateNumber().equals(444444));
    }
}