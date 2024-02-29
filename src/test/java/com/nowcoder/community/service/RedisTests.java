package com.nowcoder.community.service;

import com.nowcoder.community.CommunityApplication;
import com.nowcoder.community.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = CommunityApplication.class)
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testStrings () {
        String redisKey = "test:count";
//        value存String类xing
        redisTemplate.opsForValue().set(redisKey, 1);
        System.out.println(redisTemplate.opsForValue().get(redisKey));
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));

    }

    @Test
    public void key() {
        String a = "test:user";
        redisTemplate.opsForHash().put(a,"id", 1);
        redisTemplate.opsForHash().put(a, "username", "zhangsan");
        System.out.println(redisTemplate.opsForHash().get(a,"id"));
        System.out.println(redisTemplate.opsForHash().get(a,"username"));

        String b = "test:ids";
        redisTemplate.opsForList().leftPush(b, 101);
        redisTemplate.opsForList().leftPush(b, 102);
        redisTemplate.opsForList().leftPush(b, 103);
        redisTemplate.opsForList().index(b, 1);

        String c = "test:key";
        redisTemplate.opsForSet().add(c, "涨粉", "咯咯哒", "水分");
        redisTemplate.opsForSet().pop(c);
        redisTemplate.opsForSet().size(c);
        redisTemplate.opsForSet().members(c);

        String d = "test:students";
        redisTemplate.opsForZSet().add(d, "唐僧", 80);
//        统计数量
        System.out.println(redisTemplate.opsForZSet().zCard(d));
//        统计分数
        System.out.println(redisTemplate.opsForZSet().score(d, "唐僧"));
//        统计排名
        System.out.println(redisTemplate.opsForZSet().rank(d, "唐僧"));
//        显示范围内的书记
        System.out.println(redisTemplate.opsForZSet().range(d, 0, 2));


        redisTemplate.delete(a);

        System.out.println(redisTemplate.hasKey(a));
//   设置过期时间
        redisTemplate.expire(b, 10, TimeUnit.DAYS);

    }
//    多次访问同一个key，绑定同一个key进行操作
    @Test
    public void aaa () {
        String redisKey = "test:count";
        BoundValueOperations operations = redisTemplate.boundValueOps(redisKey);
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        System.out.println(operations.get());

    }

    @Test
    public void testTransactional() {
        Object object = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String redisKey = "test:tx";
//                开始事务
                operations.multi();
                operations.opsForSet().add(redisKey, "zhangsan", "list", "wangwu");
                System.out.println(operations.opsForSet().members(redisKey));
//                提交事务
                return operations.exec();

            }
        });

        System.out.println(object);

    }



}













