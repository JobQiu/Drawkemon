package com.letsolve.oyster.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xavier.qiu
 * 7/2/18 9:40 PM
 */
@Component
public class RedisHelper {
    public static final int REDIS_CACHE_EXPIRE_TIME = 36000;

    private boolean useRedis = true;

    //@Scheduled
    public void checkRedisConnection() {

    }

    @Autowired
    private Environment env;

    private JedisPool pool;

    @PostConstruct
    public void init() {
        pool = new JedisPool(new JedisPoolConfig(),
                env.getProperty("spring.redis.host"),
                Integer.valueOf(env.getProperty("spring.redis.port")),
                Integer.valueOf(env.getProperty("spring.redis.timeout")),
                env.getProperty("spring.redis.password"));
    }

    public void save(String key, String value) {
        Jedis j = pool.getResource();
        j.set(key, value);
        j.expire(key, REDIS_CACHE_EXPIRE_TIME);
        j.close();
    }

    public void save(String key, Integer value) {
        Jedis j = pool.getResource();
        j.set(key, value.toString());
        j.expire(key, REDIS_CACHE_EXPIRE_TIME);
        j.close();
    }


    public void save(String key, String value, int expire) {
        Jedis j = pool.getResource();
        j.set(key, value);
        j.expire(key, expire);
        j.close();
    }


    public void put(String key, String value) {
        save(key, value);
    }

    public void put(long key, String value) {
        save(String.valueOf(key), value);
    }

    public String get(String key) {
        Jedis j = pool.getResource();
        String value = j.get(key);
        j.close();
        return value;
    }

    public Map<String, String> getByPattern(String pattern) {

        Jedis j = pool.getResource();
        Set<String> keys = j.keys(pattern);
        Map<String, String> res = new HashMap<>();
        for (String key : keys) {
            res.put(key, j.get(key));
        }

        return res;


    }

    public String get(int key) {
        Jedis j = pool.getResource();
        String value = j.get(String.valueOf(key));
        j.close();
        return value;
    }


    public String get(long key) {
        Jedis j = pool.getResource();
        String value = j.get(String.valueOf(key));
        j.close();
        return value;
    }

    public boolean isExisted(String key) {
        Jedis j = pool.getResource();
        boolean result = j.exists(key);
        j.close();
        return result;
    }
}
