package com.xiaoshi.order.pojo.dto;

import lombok.Data;

/**
 *前台获取Address返回参数
 */
@Data
public class ReturnAddress {
    //地址 id
    private Long addressId;
    // 地址名称
    private String addressName;
    //邮编
    private String zipCode;
}
