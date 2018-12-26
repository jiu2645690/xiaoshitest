package com.xiaoshi.order.pojo.form;

import lombok.Data;

/**
 *获取前台分页参数
*/
@Data
public class BaseForm {
    private Integer page = 1;
    private Integer size = 10;
}
