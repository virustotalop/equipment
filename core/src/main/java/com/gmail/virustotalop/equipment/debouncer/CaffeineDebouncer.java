package com.gmail.virustotalop.equipment.debouncer;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class CaffeineDebouncer<K> implements Debouncer<K> {

    private final Cache<K, Long> cache;

    public CaffeineDebouncer(int debounceTime, @NotNull TimeUnit timeUnit) {
        this(debounceTime, timeUnit, Long.MAX_VALUE);
    }

    public CaffeineDebouncer(int debounceTime, @NotNull TimeUnit timeUnit, long maxCacheSize) {
        this.cache = Caffeine.newBuilder()
                .maximumSize(maxCacheSize)
                .expireAfterWrite(debounceTime, timeUnit)
                .build();
    }


    @Override
    public boolean containsKey(@NotNull K key) {
        return this.cache.getIfPresent(key) != null;
    }

    @Override
    public boolean debounce(@NotNull K key) {
        if(this.containsKey(key)) {
            return true;
        }
        this.cache.put(key, Instant.now().toEpochMilli());
        return false;
    }
}
