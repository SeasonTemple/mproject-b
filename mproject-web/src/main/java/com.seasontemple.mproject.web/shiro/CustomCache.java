package com.seasontemple.mproject.web.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seasontemple.mproject.dao.redis.JedisUtil;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.exception.CustomException;
import com.seasontemple.mproject.utils.token.TokenUtil;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.*;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 自定义shiro缓存类
 * @create: 2020/04/02 23:06:38
 */
public class CustomCache<K, V> implements Cache<K, V> {

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    private ObjectMapper om = new ObjectMapper();

    /**
     * 缓存的key名称获取为shiro:cache:account
     * @param key
     * @return java.lang.String
     * @author dolyw.com
     * @date 2018/9/4 18:33
     */
    private String getKey(Object key) {
        return NormalConstant.PREFIX_SHIRO_CACHE + tokenUtil.getClaim(key.toString(), NormalConstant.ACCOUNT);
    }

    /**
     * 获取缓存
     */
    @Override
    public Object get(Object key) throws CacheException {
        if(Boolean.FALSE.equals(JedisUtil.exists(this.getKey(key)))){
            return null;
        }
        return JedisUtil.getObject(this.getKey(key));
    }

    /**
     * 保存缓存
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {
        // 读取配置文件，获取Redis的Shiro缓存过期时间
        String shiroCacheExpireTime = refreshTokenExpireTime;
        // 设置Redis的Shiro缓存
        return JedisUtil.setObject(this.getKey(key), value, Integer.parseInt(shiroCacheExpireTime));
    }

    /**
     * 移除缓存
     */
    @Override
    public Object remove(Object key) throws CacheException {
        if(Boolean.FALSE.equals(JedisUtil.exists(this.getKey(key)))){
            return null;
        }
        JedisUtil.delKey(this.getKey(key));
        return null;
    }

    /**
     * 清空所有缓存
     */
    @Override
    public void clear() throws CacheException {
        Objects.requireNonNull(JedisUtil.getJedis()).flushDB();
    }

    /**
     * 缓存的个数
     */
    @Override
    public int size() {
        Long size = Objects.requireNonNull(JedisUtil.getJedis()).dbSize();
        return size.intValue();
    }

    /**
     * 获取所有的key
     */
    @Override
    public Set keys() {
        Set<byte[]> keys = Objects.requireNonNull(JedisUtil.getJedis()).keys("*".getBytes());
        Set<Object> set = new HashSet<Object>();
        for (byte[] bs : keys) {
            try {
                set.add(om.readValue(bs, Object.class));
            } catch (IOException e) {
                throw new CustomException("从Jedis获取所有key发生异常:" + e.getMessage());
            }
        }
        return set;
    }

    /**
     * 获取所有的value
     */
    @Override
    public Collection values() {
        Set keys = this.keys();
        List<Object> values = new ArrayList<Object>();
        for (Object key : keys) {
            values.add(JedisUtil.getObject(this.getKey(key)));
        }
        return values;
    }
}
