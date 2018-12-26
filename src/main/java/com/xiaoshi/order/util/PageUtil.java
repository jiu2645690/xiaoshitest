package com.xiaoshi.order.util;

import com.github.pagehelper.PageHelper;
import com.xiaoshi.order.pojo.model.PageModel;
import com.github.pagehelper.Page;
import java.util.List;

/**
 * 分页工具类
 */
public class PageUtil {

    /** 
    *  分页 工具类
    */ 
    public static PageModel setPage(List list, Page page) {
        PageModel pageModel = new PageModel();
        pageModel.setList(list);
        pageModel.setCount(page.getTotal());
        pageModel.setPageNum(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        return pageModel;
    }

    /**
    *  设置页码分页工具类
    */
    public static Page setPage(Integer pageNum, Integer pageSize) {
        Page page = PageHelper.startPage((pageNum == null) ? 1 : pageNum, (pageSize == null) ? 10 : pageSize, true);
        return page;
    }
}
