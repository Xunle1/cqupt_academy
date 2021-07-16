package com.xunle.educms;

import redis.clients.jedis.Jedis;

/**
 * @author xunle
 */
public class JedisDemo1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.248.132", 6379);

        String ping = jedis.ping();
        System.out.println(ping);
    }
}
