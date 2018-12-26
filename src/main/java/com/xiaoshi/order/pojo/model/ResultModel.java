package com.xiaoshi.order.pojo.model;

import lombok.Data;

/**
 * 返回前台数据模型
 */
@Data
public class ResultModel<T> {
    private Integer code;
    private String message;
    private T data;
}
