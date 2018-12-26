package com.xiaoshi.order.util;

import com.xiaoshi.order.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
*/
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static StringRedisTemplate redisTemplate;

    /**
     *  初始化 redisTemplate
     */
    @PostConstruct
    public void init() {
        redisTemplate = stringRedisTemplate;
    }

    /**
     * 新增数据
     */
    public static void set(String key, String value) {

        redisTemplate.opsForValue().set(key, value, RedisConstant.EXPIRE, TimeUnit.SECONDS);
    }

    /**
     * 新增用户注册的验证码数据
     */
    public static void setSignUp(String key, String value) {
        redisTemplate.opsForValue().set(key, value, RedisConstant.SIGN_UP_EXPIRE, TimeUnit.SECONDS);
    }

    /**
     * 获取数据
     */
    public static String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取ttl值
     */
    public static Long getTtl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断key是否存在
     */
    public static boolean IsExist(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     *  删除数据
     */
    public static void delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}
