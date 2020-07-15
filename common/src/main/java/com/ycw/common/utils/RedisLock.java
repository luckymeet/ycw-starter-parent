package com.ycw.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Redis分布式锁
 *
 * @author yuminjun
 */
public class RedisLock {

    private static final Logger log = LoggerFactory.getLogger(RedisLock.class);

    /**
     * Redis锁的key
     */
    private final String key;

    /**
     * 加锁的对象
     */
    private final Object lockObject;

    /**
     * redis中key的过期时间
     */
    private long timeout = 60;

    /**
     * 阻塞等待的最长时间，超过这个时间则加锁失败，当值小于等于0时为非阻塞锁
     */
    private long expire = 60;

    private RedisTemplate<String, Object> redisTemplate = (RedisTemplate) SpringUtils.getBean("redisTemplate");

    /**
     * 加锁
     *
     * @return true:加锁成功，false:加锁失败
     */
    public boolean tryLock() {
        long startTime = System.currentTimeMillis();
        while (true) {
            if (Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, lockObject, timeout, TimeUnit.SECONDS))) {
                break;
            }
            // 设置redis过期时间失败，重新设置过期时间
            if (redisTemplate.getExpire(key) == -1) {
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            long waitTime = (System.currentTimeMillis() - startTime) / 1000;
            // 等待超时，加锁失败
            if (waitTime > expire) {
                return false;
            }
            try {
                // 防止线程饥饿
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                log.error("Redis分布式锁加锁时线程睡眠异常", e);
                Thread.currentThread().interrupt();
            }
        }
        return true;
    }

    /**
     * 解锁
     */
    public void unLock() {
        redisTemplate.delete(key);
    }

    public RedisLock(String key, Object lockObject) {
        this.key = key;
        this.lockObject = lockObject;
    }

    public RedisLock(String key, Object lockObject, long timeout, long expire) {
        this.key = key;
        this.lockObject = lockObject;
        this.timeout = timeout;
        this.expire = expire;
    }

    public String getKey() {
        return key;
    }

    public Object getLockObject() {
        return lockObject;
    }

    public long getTimeout() {
        return timeout;
    }

    public long getExpire() {
        return expire;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
