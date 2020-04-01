package com.seasontemple.mproject.dao.redis;

import cn.hutool.log.StaticLog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Season Temple
 * @program: mproject
 * @description: Jedis配置类
 * @create: 2020/04/01 00:42:55
 */
@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "redis")
@Setter
@Getter
public class JedisConfig {

    private String host;

    private int port;

    private String password;

    private int timeout;

    @Value("${redis.pool.max-active}")
    private int maxActive;

    @Value("${redis.pool.max-wait}")
    private int maxWait;

    @Value("${redis.pool.max-idle}")
    private int maxIdle;

    @Value("${redis.pool.min-idle}")
    private int minIdle;

    @Bean
    public JedisPool redisPoolFactory() {
        try {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxWaitMillis(maxWait);
            jedisPoolConfig.setMaxTotal(maxActive);
            jedisPoolConfig.setMinIdle(minIdle);
            JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, null);
            StaticLog.info("初始化Redis连接池JedisPool成功! Redis地址: {}: {}", host, port);
            return jedisPool;
        } catch (Exception e) {
            StaticLog.error("初始化Redis连接池JedisPool异常: {}", e.getMessage());
        }
        return null;
    }
}


