package com.xiaoshi.order.pojo.entity;

import lombok.Data;

/**
 *  Store实体
 */
@Data
public class StoreAddressAssociation {
    //商店
    private Store store;
    //送货地址
    private Address address;
    private boolean isDeleted;

}
