package com.xiaoshi.order.constant;

public interface RedisConstant {
    /**
     * 过期时间 EXPIRE和SIGN_UP_EXPIRE都是按秒计算，如(3600 s) * 12 = 12 hours
     */
    Integer EXPIRE = 3600 * 12;
    Integer SIGN_UP_EXPIRE = 60 * 2;
}
