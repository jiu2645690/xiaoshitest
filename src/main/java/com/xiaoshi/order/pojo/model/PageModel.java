package com.xiaoshi.order.pojo.model;

import lombok.Data;
import java.util.List;

/**
 * 分页
 */
@Data
public class PageModel {
    private Integer pageNum;
    private Integer pageSize;
    private Long count;
    private List list;
}
