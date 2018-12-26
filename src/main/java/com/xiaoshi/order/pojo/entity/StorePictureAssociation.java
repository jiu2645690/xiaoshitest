package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 *StorePictureAssociation实体
*/
@Data
public class StorePictureAssociation {
    private Store store;
    private Picture picture;
    private boolean isDeleted;

}
