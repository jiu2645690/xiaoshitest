package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.FoodItemPictureAssociationDao;
import com.xiaoshi.order.dao.FoodItemDao;
import com.xiaoshi.order.dao.PictureDao;
import com.xiaoshi.order.pojo.entity.FoodItem;
import com.xiaoshi.order.pojo.entity.FoodItemPictureAssociation;
import com.xiaoshi.order.pojo.entity.Picture;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FoodItemPictureAssociationDaoTest {

    @Autowired
    private FoodItemPictureAssociationDao foodItemPictureAssociationDao;
    @Autowired
    private FoodItemDao foodItemDao;
    @Autowired
    private PictureDao pictureDao;

    @Test
    public void select() {
        assertNotNull(foodItemPictureAssociationDao.selectList());
    }

    @Test
    public void insert() {
        Map map = new HashMap();
        map.put("foodItemId",-1L);
        map.put("pictureId",-1L);
        FoodItem foodItem=new FoodItem();
        foodItem.setFoodItemId(-1L);
        Picture picture=new Picture();
        picture.setPictureId(-1L);
        FoodItemPictureAssociation foodItemPictureAssociation = new FoodItemPictureAssociation();
        foodItemPictureAssociation.setFoodItem(foodItem);
        foodItemPictureAssociation.setPicture(picture);
        foodItemPictureAssociationDao.insert(foodItemPictureAssociation);
        TestCase.assertTrue(foodItemPictureAssociationDao.getFoodItemPictureAssociationByParameter(map).size()>=1);
    }
}