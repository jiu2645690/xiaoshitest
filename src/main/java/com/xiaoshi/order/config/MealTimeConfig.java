package com.xiaoshi.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 时间配置
 **/
@Configuration
@ConfigurationProperties(prefix = "mealtime")
@Data
public class MealTimeConfig {
    private String lunchSetTime;
    private String dinnerSetTime;

}