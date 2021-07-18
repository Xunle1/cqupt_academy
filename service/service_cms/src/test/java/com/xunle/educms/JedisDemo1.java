package com.xunle.educms;

import redis.clients.jedis.Jedis;

/**
 * 测试redis连接
 * @author xunle
 */

public class JedisDemo1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        String ping = jedis.ping();
        System.out.println(ping);
    }
}
