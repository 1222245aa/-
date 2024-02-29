package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {
//对配置类进行重配，原本的配置不好用
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        通过工厂与数据库连接
        template.setConnectionFactory(factory);

//        设置key的序列化方式
        template.setKeySerializer(RedisSerializer.string());
//        设置Value的序列化方式
        template.setValueSerializer(RedisSerializer.json());
//        设置hash的key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
//        设置hash的value的序列化方式,因为value的形式可以有很多种，所以用json字符串
        template.setHashValueSerializer(RedisSerializer.json());
//        使这些配置生效
        template.afterPropertiesSet();

        return template;
    }

}
