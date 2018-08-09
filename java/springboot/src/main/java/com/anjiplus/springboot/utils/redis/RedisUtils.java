package com.anjiplus.springboot.utils.redis;



import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

/**
 * <p>
 *
 * </p>
 * @author Chenyuqiang
 * @version $Id: RedisUtils.java, v 0.1 2017年5月16日 下午1:28:21 chenyuqiang Exp $
 */
@Configuration
public class RedisUtils {

    @Resource
    private   RedisTemplate<String, String> redisTemplate;

    private static String                 redisCode = "utf-8";

    /**
     * @param keys
     *            k
     * @return l
     */
    public long del(final String... keys) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    try {
                        result += connection.del(keys[i].getBytes(redisCode));
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return result;
            }
        });
    }

    /**
     * @since 1.0
     * @param key
     *            k
     * @param value
     *            v
     * @param liveTime
     */
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * @since 1.0
     * @param key
     *            k
     * @param value
     *            v
     * @param liveTime
     */
    public void set(String key, String value, long liveTime) {
        try {
            this.set(key.getBytes(redisCode), value.getBytes(redisCode), liveTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key
     *            k
     * @param value
     *            v
     */
    public void set(String key, String value) {
        this.set(key, value, 0L);
    }

    /**
     * @param key
     *            k
     * @param value
     *            v
     */
    public void set(byte[] key, byte[] value) {
        this.set(key, value, 0L);
    }

    /**
     * @param key
     *            l
     * @return s
     */
    public String get(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] data = connection.get(key.getBytes(redisCode));
                    if (data != null) {
                        return new String(data, redisCode);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    /**
     * @param pattern
     *            p
     * @return set
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);

    }

    /**
     * @param key
     *            k
     * @return b
     */
    public boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    return connection.exists(key.getBytes(redisCode));
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    /**
     * @return s
     */
    public String flushDB() {
        return flush();
    }
    public  String flush() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    /**
     * @return l
     */
    public long dbSize() {
       return dbs();
    }
    public  long dbs() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * @return s
     */
    public String ping() {
       return pingRedis();
    }
    public  String pingRedis() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }

    /**
     * @since 1.0
     * @param key
     *            k
     * @return
     */
    public Object getObject(final String key) {
        return SerializationUtils.deserialize(redisTemplate.execute(new RedisCallback<byte[]>() {
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                    try {
                        return connection.get(key.getBytes(redisCode));
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    byte[] str = null;
                return str;
            }
        }));
    }
}
