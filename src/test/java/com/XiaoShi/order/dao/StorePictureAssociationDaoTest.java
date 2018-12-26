package com.XiaoShi.order.dao;

import com.xiaoshi.order.dao.StorePictureAssociationDao;
import com.xiaoshi.order.pojo.entity.StorePictureAssociation;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StorePictureAssociationDaoTest {

    @Autowired
    private StorePictureAssociationDao storePictureAssociationDao;

    @Test
    public void select() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("storeId","1");
        map.put("pictureId","1");
        List<StorePictureAssociation> storePictureAssociation=storePictureAssociationDao.select(map);
        assertNotNull(storePictureAssociation);
        TestCase.assertTrue(storePictureAssociation.size()>=1);
    }

}