package com.gmail.virustotalop.equipment.debouncer;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class CaffeineDebouncer<K> implements Debouncer<K> {

    private final Cache<K, Long> cache;

    public CaffeineDebouncer(int debounceTime, TimeUnit timeUnit) {
        this(debounceTime, timeUnit, Long.MAX_VALUE);
    }

    public CaffeineDebouncer(int debounceTime, TimeUnit timeUnit, Long maxCacheSize) {
        this.cache = Caffeine.newBuilder()
                .maximumSize(maxCacheSize)
                .expireAfterWrite(debounceTime, timeUnit)
                .build();
    }


    @Override
    public boolean wouldDebounce(K key) {
        return this.cache.getIfPresent(key) != null;
    }

    @Override
    public boolean debounce(K key) {
        if(this.wouldDebounce(key)) {
            return true;
        }
        this.cache.put(key, Instant.now().toEpochMilli());
        return false;
    }
}
