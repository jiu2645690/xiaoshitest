package com.XiaoShi.order.service;

import com.xiaoshi.order.service.OrderService;
import com.xiaoshi.order.service.ComboService;
import com.xiaoshi.order.service.StorePictureAssociationService;
import com.xiaoshi.order.service.ComboTemplateService;
import com.xiaoshi.order.service.StoreService;
import com.xiaoshi.order.service.PictureService;
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
public class StorePictureAssociationServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ComboService comboService;
    @Autowired
    private StorePictureAssociationService storePictureAssociationService;
    @Autowired
    private ComboTemplateService comboTemplateService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PictureService pictureService;

    @Test
    public void get() {
        assertNotNull(storePictureAssociationService.getStorePictureAssociationList());
    }
}