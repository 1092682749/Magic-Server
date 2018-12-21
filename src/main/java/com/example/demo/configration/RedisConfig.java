package com.example.demo.configration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.PoolConfig;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import redis.clients.jedis.Jedis;

import java.util.concurrent.Executor;

@Configuration
@EnableCaching
@EnableAsync
public class RedisConfig extends AsyncConfigurerSupport {

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String,String>();

        redisTemplate.setConnectionFactory(factory);
        // key序列化方式;（不然会出现乱码;）
        // value使用 jdkSerializationRedisSerializer 序列化方式
        // key使用 String RedisSerializer序列化方式
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();// Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
//        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
//        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(200);
        taskExecutor.initialize();
        return taskExecutor;
    }
    @Bean
    public Jedis connectRedis() {
        Jedis jedis = new Jedis("localhost",6379);
        float f = 1;
        return jedis;



    }
}
