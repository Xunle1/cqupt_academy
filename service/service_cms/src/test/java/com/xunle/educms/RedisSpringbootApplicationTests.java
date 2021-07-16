package com.xunle.educms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xunle
 */
@SpringBootTest
public class RedisSpringbootApplicationTests {
    @Autowired
    public RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        redisTemplate.opsForValue().set("mykey","xunle");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }
}
