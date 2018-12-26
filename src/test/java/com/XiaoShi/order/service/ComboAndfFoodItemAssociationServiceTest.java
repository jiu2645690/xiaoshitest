package com.XiaoShi.order.service;

import com.xiaoshi.order.pojo.entity.ComboAndfFoodItemAssociation;
import com.xiaoshi.order.service.ComboAndfFoodItemAssociationService;
import com.xiaoshi.order.service.ComboService;
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
public class ComboAndfFoodItemAssociationServiceTest {

    @Autowired
    private ComboAndfFoodItemAssociationService comboAndfFoodItemAssociationService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private FoodItemService foodItemService;

    @Test
    public void get() {
        assertNotNull(comboAndfFoodItemAssociationService.get(1L));
    }

    @Test
    public void insertComboAndfFoodItemAssociation() {
        ComboAndfFoodItemAssociation comboAndfFoodItemAssociation = new ComboAndfFoodItemAssociation();
        comboAndfFoodItemAssociation.setCombo(comboService.get(1L));
        comboAndfFoodItemAssociation.setFoodItem(foodItemService.get(1L));
        comboAndfFoodItemAssociationService.insertComboAndfFoodItemAssociation(comboAndfFoodItemAssociation);
        TestCase.assertTrue(comboAndfFoodItemAssociationService.get(comboAndfFoodItemAssociation.getCombo().getComboId()).getCombo().getComboId().equals(comboAndfFoodItemAssociation.getCombo().getComboId()));
    }

    @Test
    public void updateComboAndfFoodItemAssociation() {
        ComboAndfFoodItemAssociation comboAndfFoodItemAssociation = comboAndfFoodItemAssociationService.get(1L);
        comboAndfFoodItemAssociation.setIsDeleted(true);
        comboAndfFoodItemAssociation.setFoodItemCount(2);
        comboAndfFoodItemAssociationService.updateComboAndfFoodItemAssociation(comboAndfFoodItemAssociation);
        TestCase.assertTrue(comboAndfFoodItemAssociationService.get(comboAndfFoodItemAssociation.getCombo().getComboId()).getFoodItemCount().equals(2));
    }
}