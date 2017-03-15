package com.teardowall.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class Redis {
	//private static Logger logger = LoggerFactory.getLogger(CacheKit.class);  
    //private List<JSONObject> resultList;  
    private static JedisPool pool;  
  
    /** 
     * 初始化Redis连接池 
     * @throws IOException 
     */  
    private static void initializePool() throws IOException {  
        //redisURL 与 redisPort 的配置文件  
        String configFile = "redis.properties";
        Properties prop = new Properties();
        //FileInputStream fi = new FileInputStream(configFile);
        //InputStream in = Redis.class.getClass().getResourceAsStream(configFile);
        InputStream in = Redis.class.getClassLoader().getResourceAsStream(configFile);
        prop.load(in);
  
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数（100个足够用了，没必要设置太大）  
        config.setMaxTotal(Integer.parseInt(prop.getProperty("jedis.pool.maxActive")));
        //最大空闲连接数  
        config.setMaxIdle(Integer.parseInt(prop.getProperty("jedis.pool.maxIdle")));
        //获取Jedis连接的最大等待时间（50秒）   
        config.setMaxWaitMillis(Integer.parseInt(prop.getProperty("jedis.pool.maxWait")));
        //在获取Jedis连接时，自动检验连接是否可用  
        config.setTestOnBorrow(Boolean.parseBoolean(prop.getProperty("jedis.pool.testOnBorrow")));
        //在将连接放回池中前，自动检验连接是否有效  
        config.setTestOnReturn(Boolean.parseBoolean(prop.getProperty("jedis.pool.testOnBorrow")));
        //自动测试池中的空闲连接是否都是可用连接  
        config.setTestWhileIdle(true);
        //创建连接池  
        pool = new JedisPool(config, prop.getProperty("redis.ip"),  
        		Integer.parseInt(prop.getProperty("redis.port")));  
        in.close();
    }  
  
    /** 
     * 多线程环境同步初始化（保证项目中有且仅有一个连接池） 
     */  
    private static synchronized void poolInit() {  
        if (null == pool) {  
            try {
				initializePool();
			} catch (IOException e) {
				pool = null;
				e.printStackTrace();
			}  
        }  
    }  
  
    /** 
     * 获取Jedis实例 
     */  
    public static Jedis getJedis() {  
        if (null == pool) {  
            poolInit();
        }  
  
        int timeoutCount = 0;  
        while (true) {  
            try {  
                if (null != pool) {  
                    return pool.getResource();  
                }  
            } catch (Exception e) {  
                if (e instanceof JedisConnectionException) {  
                    timeoutCount++;  
                    //logger.warn("getJedis timeoutCount={}", timeoutCount);  
                    if (timeoutCount > 3) {  
                        break;  
                    }  
                } else {  
//                    logger.warn("jedisInfo ... NumActive=" + pool.getNumActive()  
//                            + ", NumIdle=" + pool.getNumIdle()  
//                            + ", NumWaiters=" + pool.getNumWaiters()  
//                            + ", isClosed=" + pool.isClosed());  
//                    logger.error("GetJedis error,", e);  
                    break;  
                }  
            }  
            break;  
        }  
        return null;  
    }  
  
    /** 
     * 释放Jedis资源 
     * 
     * @param jedis 
     */  
    public static void returnResource(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
  
    /** 
     * 绝对获取方法（保证一定能够使用可用的连接获取到 目标数据） 
     * Jedis连接使用后放回  
     * @param key 
     * @return 
     */  
    private String safeGet(String key) {  
        Jedis jedis = getJedis();  
        while (true) {  
            if (null != jedis) {  
                break;  
            } else {  
                jedis = getJedis();  
            }  
        }  
        String value = jedis.get(key);  
        returnResource(jedis);  
        return value;  
    }  
  
    /** 
     * 绝对设置方法（保证一定能够使用可用的链接设置 数据） 
     * Jedis连接使用后返回连接池 
     * @param key 
     * @param time 
     * @param value 
     */  
    private void safeSet(String key, int time, String value) {  
        Jedis jedis = getJedis();  
        while (true) {  
            if (null != jedis) {  
                break;  
            } else {  
                jedis = getJedis();  
            }  
        }  
        jedis.setex(key, time, value);  
        returnResource(jedis);  
    }  
  
    /** 
     * 绝对删除方法（保证删除绝对有效） 
     * Jedis连接使用后返回连接池</span> 
     * @param key 
     */  
    private void safeDel(String key) {  
        Jedis jedis = getJedis();  
        while (true) {  
            if (null != jedis) {  
                break;  
            } else {  
                jedis = getJedis();  
            }  
        }  
        jedis.del(key);  
        returnResource(jedis);  
    }  
  
    /**自定义的一些 get set del 方法，方便使用**/  
    public JSONObject getByCache(String key) {  
        String result = safeGet(key);  
        if (result != null) {  
            return (JSONObject) JSONObject.parse(result);  
        }  
        return null;  
  
    }  
  
    public String getByCacheToString(String key) {  
        String result = safeGet(key);  
        if (result != null) {  
            return result;  
        }  
        return null;  
  
    }  
  
//    public List<JSONObject> getArrayByCache(String key) {  
//        String result = safeGet(key);  
//        if (result != null) {  
//            resultList = JSONArray.parseArray(result, JSONObject.class);  
//            return resultList;  
//        }  
//        return null;  
//    }  
//  
//    public JSONArray getJSONArrayByCache(String key) {  
//        String result = safeGet(key);  
//        if (result != null) {  
//            return JSONArray.parseArray(result);  
//        }  
//        return null;  
//    }  
//  
//    public void setByCache(String key, String s) {  
//        safeSet(key, 86400, s);  
//    }  
//  
//    public void setByCacheOneHour(String key, String s) {  
//        safeSet(key, 3600, s);  
//    }  
//  
//    public void setByCacheOneHour(String key, List<JSONObject> json) {  
//        safeSet(key, 86400, JSONObject.toJSONString(json));  
//        resultList = json;  
//    }  
//  
//    public void setByCache(String key, JSONObject json) {  
//        safeSet(key, 86400, JSONObject.toJSONString(json));  
//    }  
//  
//    public void setByCache(String key, List<JSONObject> list) {  
//        safeSet(key, 86400, JSONObject.toJSONString(list));  
//        resultList = list;  
//    }  
//  
//    public void setByCache(String key, JSONArray array) {  
//        safeSet(key, 86400, JSONArray.toJSONString(array));  
//    }  
//  
//    public void setByCacheCusTime(String key, String s, int time) {  
//        safeSet(key, time, s);  
//    }  
//  
//  
//    public void delByCache(String key) {  
//        if (null != safeGet(key)) {  
//            safeDel(key);  
//        }  
//    }  
//  
//    public JSONObject toJSON(DBObject db) {  
//        return (JSONObject) JSONObject.toJSON(db);  
//    }  
//  
//    public List<JSONObject> toJSON(List<DBObject> list) {  
//        List<JSONObject> json = new ArrayList<>();  
//        for (DBObject aList : list) {  
//            json.add((JSONObject) JSONObject.toJSON(aList));  
//        }  
//        return json;  
//    }  
//  
//    public boolean notNull() {  
//        return resultList != null && resultList.size() > 0;  
//    }  
//  
//    public List<JSONObject> getResult() {  
//        return resultList;  
//    }  
}
