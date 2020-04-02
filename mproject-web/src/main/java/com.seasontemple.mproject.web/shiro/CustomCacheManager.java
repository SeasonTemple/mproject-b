package com.seasontemple.mproject.web.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 自定义shiro缓存中间类
 * @create: 2020/04/02 23:04:31
 */
public class CustomCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return null;
    }
}
