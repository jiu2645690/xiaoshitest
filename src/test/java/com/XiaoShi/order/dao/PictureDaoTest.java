package com.XiaoShi.order.dao;

import com.xiaoshi.order.pojo.entity.Picture;
import com.xiaoshi.order.dao.PictureDao;
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
public class PictureDaoTest {

    @Autowired
    private PictureDao pictureDao;

    @Test
    public void select() {
        Picture picture = pictureDao.select(1L);
        assertNotNull(picture);
    }

    @Test
    public void insert() {
        Picture picture = new Picture();
        picture.setPictureUrl("http://www.baidu.com.cc");
        pictureDao.insert(picture);
        TestCase.assertTrue(pictureDao.select(picture.getPictureId()).getPictureId().equals(picture.getPictureId()));
    }

}