package com.tcb.vru_service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>[功能描述]：连接redis服务的工具类</p>
 * <p>Copyright (c) 2020-2030 TCB Corporation</p>
 *
 * @author 王垒
 * @version 1.0, 2020年04月22日14:03:17
 * @since env_gateway 1.0.0
 */
public final class RedisUtil {

    /**
     * 日志输出标记
     */
    private static final String LOG = "RedisUtil";
    /**
     * 声明日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    //Redis服务器IP
    private static String ADDR = "127.0.0.1";

    //Redis的端口号
    private static int PORT = 6379;

    //Redis的密码
    public static String PWD;

    //可用连接实例的最大数目，默认值为8；
    //最大连接数
    private static int MAX_TOTAL = 1000;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 100;
    private static int MIN_IDLE = 50;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;
    private static Jedis jedis = null;

    public static void initJedisPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMinIdle(MIN_IDLE);//设置最小空闲数
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            config.setTestOnReturn(true);
            //Idle时进行连接扫描
            config.setTestWhileIdle(true);
            //表示idle object evitor两次扫描之间要sleep的毫秒数
            config.setTimeBetweenEvictionRunsMillis(30000);
            //表示idle object evitor每次扫描的最多的对象数
            config.setNumTestsPerEvictionRun(50);
            //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
            config.setMinEvictableIdleTimeMillis(60000);
            if (PWD != null && !PWD.isEmpty()) {
                jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, PWD);
            } else {
                jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedis != null) {
                if (!jedis.isConnected()) {
                    returnResource();
                    if (jedisPool != null) {
                        jedis = jedisPool.getResource();
                    } else {
                        initJedisPool();
                        jedis = jedisPool.getResource();
                    }
                }
            } else {
                if (jedisPool != null) {
                    jedis = jedisPool.getResource();
                } else {
                    initJedisPool();
                    jedis = jedisPool.getResource();
                }
            }
            return jedis;
        } catch (Exception e) {
            returnResource();
            logger.error(LOG + "：初始化内存数据库错误，原因：" + e.getMessage());
            return null;
        }
    }

    /**
     * <p>[功能描述]：批量获取redis列表数据</p>
     *
     * @param key
     * @param index
     * @param count
     * @return
     * @author 王垒, 2018年6月29日上午11:44:58
     * @since env_gateway 1.0.0
     */
    public static List<byte[]> getLRange(String key, int index, int count) {
        List<byte[]> byteList = null;
        Jedis jedis = null;
        try {
            if (jedisPool == null) {
                initJedisPool();
            }
            jedis = jedisPool.getResource();
            byteList = jedis.lrange(key.getBytes(), index, count);
        } catch (Exception e) {
            //释放redis对象
            if (jedis != null) {
                jedis.close();
            }
            logger.error(LOG + "：获取内存数据库数据错误，原因：" + e.getMessage());
        } finally {
            //返还到连接池        	
            if (jedis != null) {
                jedis.close();
            }
        }

        return byteList;
    }

    /**
     * <p>[功能描述]：存入redis列表对象</p>
     *
     * @param key
     * @param bytes
     * @return
     * @author 王垒, 2018年6月29日上午11:46:58
     * @since env_gateway 1.0.0
     */
    public static long setRPush(String key, byte[] bytes) {
        Jedis jedis = null;
        long value = 0;
        try {
            if (jedisPool == null) {
                initJedisPool();
            }
            jedis = jedisPool.getResource();
            value = jedis.rpush(key.getBytes(), bytes);
        } catch (Exception e) {
            //释放redis对象
            if (jedis != null) {
                jedis.close();
            }
            logger.error(LOG + "：存入内存数据库数据错误，原因：" + e.getMessage());
        } finally {
            //返还到连接池
            if (jedis != null) {
                jedis.close();
            }
        }

        return value;
    }

    /**
     * <p>[功能描述]：删除redis列表对象</p>
     *
     * @param key
     * @param count
     * @param bytes
     * @return
     * @author 王垒, 2018年6月29日上午11:48:58
     * @since env_gateway 1.0.0
     */
    public static long setLRem(String key, int count, byte[] bytes) {
        Jedis jedis = null;
        long value = 0;
        try {
            if (jedisPool == null) {
                initJedisPool();
            }
            jedis = jedisPool.getResource();
            value = jedis.lrem(key.getBytes(), count, bytes);
        } catch (Exception e) {
            //释放redis对象
            if (jedis != null) {
                jedis.close();
            }
            logger.error(LOG + "：删除内存数据库数据错误，原因：" + e.getMessage());
        } finally {
            //返还到连接池
            if (jedis != null) {
                jedis.close();
            }
        }

        return value;
    }


    /**
     * 取出缓存数据并删除
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<byte[]> getListMultValueAfterDel(String key, int start, int end) {
        List<Object> list = null;
        List<byte[]> byteList = null;
        Jedis jedis = null;
        try {
            if (jedisPool == null) {
                initJedisPool();
            }
            jedis = jedisPool.getResource();
            Transaction ts = jedis.multi();
            ts.lrange(key.getBytes(), start, end);
            ts.ltrim(key, end + 1, -1);
            list = ts.exec();
        } catch (Exception e) {
            //释放redis对象
            if (jedis != null) {
                jedis.close();
            }
            logger.error(LOG + "：获取内存数据库数据错误，原因：" + e.getMessage());
        } finally {
            //返还到连接池
            if (jedis != null) {
                jedis.close();
            }
        }
        if (list != null && !list.isEmpty()) {
            try {
                //获得命令lrange(key, start, end)的返回结果
                byteList = (ArrayList<byte[]>) list.get(0);
            } catch (Exception e) {
                logger.error(LOG + "：获取内存数据库数据错误，原因：" + e.getMessage());
            }
        }
        return byteList;
    }

    /**
     * 释放jedis资源
     */
    public static void returnResource() {
        if (jedis != null) {
            jedis.close();
            jedis = null;
        }
    }
}
