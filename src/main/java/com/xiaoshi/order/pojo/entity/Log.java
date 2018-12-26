package com.xiaoshi.order.pojo.entity;

import lombok.Data;
import java.util.Date;

/**
 * 1主要记录 后台员工的操作记录，对系统哪些板块进行了 什么样的操作。 2 运行时 系统中出现的错误，并可还原 是由 商家/用户 进行了 什么操作。
 */
@Data
public class Log {
    //日志主键id
    private Long logId;
    //日志类型 必填
    private Integer logType;
    //创建时间
    private Date createdAt;
    //日志内容 必填
    private String logContent;
    //操作人员类型
    private Integer peopleType;
    //操作人员id
    private Long peopleId;
    //日志类型
    private String ip;
    // 是否修复 错误
    private boolean isRepair;
    //修复内容
    private String repairConten;
}
