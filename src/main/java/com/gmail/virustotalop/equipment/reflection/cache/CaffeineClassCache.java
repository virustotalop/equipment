package com.gmail.virustotalop.equipment.reflection.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class CaffeineClassCache implements ClassCache {

    private final Cache<String, Optional<Class<?>>> cache;

    public CaffeineClassCache(int cacheTimeout, TimeUnit timeUnit) {
        this(cacheTimeout, timeUnit, Long.MAX_VALUE);
    }

    public CaffeineClassCache(int cacheTimeout, TimeUnit timeUnit, Long maxCacheSize) {
        this.cache = Caffeine.newBuilder()
                .maximumSize(maxCacheSize)
                .expireAfterWrite(cacheTimeout, timeUnit)
                .build();
    }

    @Override
    public Map<String, Optional<Class<?>>> cache() {
        return this.cache.asMap();
    }
}
