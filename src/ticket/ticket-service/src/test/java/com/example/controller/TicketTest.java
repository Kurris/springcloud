package com.example.controller;

import com.example.common.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class TicketTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void aaa() {
        System.out.println(1111);
        redisUtil.set("name", "ligy", 100);
    }
}