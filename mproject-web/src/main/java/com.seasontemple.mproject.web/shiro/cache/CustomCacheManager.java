package com.seasontemple.mproject.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 自定义shiro缓存中间类
 */
public class CustomCacheManager implements CacheManager {

    @Resource
    private CustomCache customCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return customCache;
    }
}
