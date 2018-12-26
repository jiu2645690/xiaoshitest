package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.Picture;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;

public interface PictureService {
    Picture get(Long id);

    List<Picture> getPictureList();

    PageModel search();

    void insertPicture(Picture picture);
}
