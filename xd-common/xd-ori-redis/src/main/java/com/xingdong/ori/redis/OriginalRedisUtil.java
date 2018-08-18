package com.xingdong.ori.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis工具类
 * Created by liushuangbo on 2018/8/17.
 */
public class OriginalRedisUtil {
    private JedisPool jedisPool;
    private static final OriginalRedisUtil jedisUtil = new OriginalRedisUtil();

//    static {
//        Properties properties = PropertyUtil.loadProperties("redis.properties");
//        String host = properties.getProperty("redis.host");
//        String port = properties.getProperty("redis.port");
////        String pass = properties.getProperty("redis.pass");
//        String timeout = properties.getProperty("redis.timeout");
//        String maxIdle = properties.getProperty("redis.maxIdle");
//        String maxTotal = properties.getProperty("redis.maxTotal");
//        String maxWaitMillis = properties.getProperty("redis.maxWaitMillis");
//        String testOnBorrow = properties.getProperty("redis.testOnBorrow");
//
//        JedisPoolConfig config = new JedisPoolConfig();
//        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
//        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//        config.setMaxTotal(Integer.parseInt(maxTotal));
//        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
//        config.setMaxIdle(Integer.parseInt(maxIdle));
//        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
//        config.setMaxWaitMillis(Long.parseLong(maxWaitMillis));
//        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//        config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
//
////        jedisPool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout), pass);
//        jedisPool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout));
////        jedis = new Jedis("localhost");
//        System.out.println("redis connected success!");
//    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static OriginalRedisUtil getInstance() {
        return jedisUtil;
    }

    /**
     * 回收jedis(放到finally中)
     *
     */
    private void returnJedis(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedis.close();
        }
    }

    /**
     * redis是否运行
     */
    public void isRuning() {
        Jedis jedis = getJedis();
        System.out.println("server can run");
        System.out.println(jedis.ping());
        returnJedis(jedis);
    }

    /**
     * 添加sorted set
     */
    public void zadd(String key, String value, double score) {
        Jedis jedis = getJedis();
        jedis.zadd(key, score, value);
        returnJedis(jedis);
    }

    /**
     * 返回指定位置的集合元素,0为第一个元素，-1为最后一个元素
     */
    public Set<String> zrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.zrange(key, start, end);
        returnJedis(jedis);
        return set;
    }

    /**
     * 获取给定区间的元素，原始按照权重由高到低排序
     */
    public Set<String> zrevrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.zrevrange(key, start, end);
        returnJedis(jedis);
        return set;
    }

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖
     *
     * @param map 对应关系
     * @return 状态，成功返回OK
     */
    public String hmset(String key, Map<String, String> map) {
        Jedis jedis = getJedis();
        String s = jedis.hmset(key, map);
        returnJedis(jedis);
        return s;
    }

    /**
     * 获取hash散列集合指定字段value
     */
    public String hget(String key, String member) {
        Jedis jedis = getJedis();
        String set = jedis.hget(key, member);
        returnJedis(jedis);
        return set;
    }

    /**
     * 获取hash所有字段
     */
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = getJedis();
        Map<String, String> map = jedis.hgetAll(key);
        returnJedis(jedis);
        return map;
    }

    /**
     * String 类型
     */
    public String setStr(String k, String v) {
        Jedis jedis = getJedis();
        String s = jedis.set(k, v);
        returnJedis(jedis);
        return s;
    }

    /**
     * String 类型
     */
    public String getStr(String k) {
        Jedis jedis = getJedis();
        String s = jedis.get(k);
        returnJedis(jedis);
        return s;
    }

    /**
     * 向List头部追加记录
     *
     * @return 记录总数
     */
    public long rpush(String key, String value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        returnJedis(jedis);
        return count;
    }

    /**
     * 获取List集合
     */
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = getJedis();
        List<String> list = jedis.lrange(key, start, end);
        returnJedis(jedis);
        return list;
    }

    public Set<String> smembers(String key) {
        Jedis jedis = getJedis();
        Set<String> sets = jedis.smembers(key);
        returnJedis(jedis);
        return sets;
    }

    /**
     * 向List头部追加记录
     *
     * @return 记录总数
     */
    private long rpush(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        returnJedis(jedis);
        return count;
    }

    /**
     * 删除
     */
    public long del(String key) {
        Jedis jedis = getJedis();
        long s = jedis.del(key);
        returnJedis(jedis);
        return s;
    }

    /**
     * 移除有序集合中一个或多个指定值
     * 从集合中删除成员
     *
     * @return 返回1成功
     */
    public long zrem(String key, String... value) {
        Jedis jedis = getJedis();
        long s = jedis.zrem(key, value);
        returnJedis(jedis);
        return s;
    }

    /**
     * 在指定db中设置String类型数据和超时时间
     * @param dbIndex 指定db
     * @param key key
     * @param value value
     * @param expireTime 超时时间
     */
    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime)
            throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0)
                jedis.expire(key, expireTime);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    /**
     * 在指定db中获取指定String类型 value
     * @param dbIndex 指定db
     * @param key key
     * @return String value
     */
    public String getValueByKey(int dbIndex, String key) throws Exception {
        Jedis jedis = null;
        String result = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }

    /**
     * 在指定db中删除指定键值对
     * @param dbIndex db
     * @param key key
     */
    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.del(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    public void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null)
            return;
        if (isBroken)
            jedisPool.returnBrokenResource(jedis);
        else
            jedisPool.returnResource(jedis);
    }

    /**
     * 获取有序集合sorted set总数量
     *
     */
    public long zcard(String key) {
        Jedis jedis = getJedis();
        long count = jedis.zcard(key);
        returnJedis(jedis);
        return count;
    }

    /**
     * 是否存在KEY
     */
    public boolean exists(String key) {
        Jedis jedis = getJedis();
        boolean exists = jedis.exists(key);
        returnJedis(jedis);
        return exists;
    }

    /**
     * 重命名KEY
     */
    public String rename(String oldKey, String newKey) {
        Jedis jedis = getJedis();
        String result = jedis.rename(oldKey, newKey);
        returnJedis(jedis);
        return result;
    }

    /**
     * 设置失效时间
     */
    public void expire(String key, int seconds) {
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        returnJedis(jedis);
    }

    /**
     * 删除失效时间
     */
    public void persist(String key) {
        Jedis jedis = getJedis();
        jedis.persist(key);
        returnJedis(jedis);
    }

    /**
     * 添加一个键值对，如果键存在不在添加，如果不存在，添加完成以后设置键的有效期
     */
    public void setnxWithTimeOut(String key, String value, int timeOut) {
        Jedis jedis = getJedis();
        if (0 != jedis.setnx(key, value)) {
            jedis.expire(key, timeOut);
        }
        returnJedis(jedis);
    }

    /**
     * 返回指定key序列值
     * 设置自增值，如果未设置，则初始化未0
     * 自增+1
     */
    public long incr(String key) {
        Jedis jedis = getJedis();
        long l = jedis.incr(key);
        returnJedis(jedis);
        return l;
    }

    /**
     * 获取当前时间
     *
     * @return 秒
     */
    public long currentTimeSecond() {
        Long l = 0l;
        Jedis jedis = getJedis();
        Object obj = jedis.eval("return redis.call('TIME')", 0);
        if (obj != null) {
            List<String> list = (List) obj;
            l = Long.valueOf(list.get(0));
        }
        returnJedis(jedis);
        return l;
    }
}
