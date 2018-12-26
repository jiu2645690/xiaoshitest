package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.ComboAndfFoodItemAssociationDao;
import com.xiaoshi.order.dao.StoreDao;
import com.xiaoshi.order.dao.CustomerDao;
import com.xiaoshi.order.dao.ComboDao;
import com.xiaoshi.order.dao.FoodItemDao;
import com.xiaoshi.order.dao.ComboTemplateDao;
import com.xiaoshi.order.pojo.entity.ComboAndfFoodItemAssociation;
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
public class ComboAndfFoodItemAssociationDaoTest {

    @Autowired
    private ComboAndfFoodItemAssociationDao comboAndfFoodItemAssociationDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ComboDao comboDao;
    @Autowired
    private ComboTemplateDao comboTemplateDao;
    @Autowired
    private FoodItemDao foodItemDao;

    @Test
    public void select() {
        ComboAndfFoodItemAssociation comboAndfFoodItemAssociation = comboAndfFoodItemAssociationDao.select(1L);
        assertNotNull(comboAndfFoodItemAssociation);
    }

    @Test
    public void insert() {
        ComboAndfFoodItemAssociation comboAndfFoodItemAssociation = new ComboAndfFoodItemAssociation();
        comboAndfFoodItemAssociation.setCombo(comboDao.select(1L));
        comboAndfFoodItemAssociation.setFoodItem(foodItemDao.select(1L));
        comboAndfFoodItemAssociationDao.insert(comboAndfFoodItemAssociation);
        TestCase.assertTrue(comboAndfFoodItemAssociationDao.select(comboAndfFoodItemAssociation.getCombo().getComboId()).getCombo().getComboId().equals(1));
    }

    @Test
    public void update() {
        ComboAndfFoodItemAssociation comboAndfFoodItemAssociation = comboAndfFoodItemAssociationDao.select(1L);
        comboAndfFoodItemAssociation.setIsDeleted(true);
        comboAndfFoodItemAssociation.setFoodItemCount(2);
        comboAndfFoodItemAssociationDao.updateComboAndfFoodItemAssociation(comboAndfFoodItemAssociation);
        TestCase.assertTrue(comboAndfFoodItemAssociationDao.select(1L).getFoodItemCount().equals(2));
    }

}