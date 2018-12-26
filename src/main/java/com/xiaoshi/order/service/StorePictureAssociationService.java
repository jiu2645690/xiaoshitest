package com.xiaoshi.order.service;

import com.xiaoshi.order.pojo.entity.StorePictureAssociation;
import com.xiaoshi.order.pojo.model.PageModel;
import java.util.List;
import java.util.Map;

public interface StorePictureAssociationService {
    List<StorePictureAssociation> get(Map<String, String> map);

    List<StorePictureAssociation> getStorePictureAssociationList();

    PageModel search();
}
