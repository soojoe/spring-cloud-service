package com.soojoe.scaffold.service.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis配置
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/15 14:56
 */
@Configuration
@ConditionalOnProperty(value = "spring.redis.host")
public class RedisConfig {
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        // 使用StringRedisTemplate模板，不需要配置key和value的序列化方式
        return new StringRedisTemplate(factory);
    }
}
