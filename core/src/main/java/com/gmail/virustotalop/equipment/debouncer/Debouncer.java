package com.gmail.virustotalop.equipment.debouncer;

import org.jetbrains.annotations.NotNull;

public interface Debouncer<K> {

    /**
     * @param key K to check the cache for
     * @return if the cache contains the specified key
     */
    boolean containsKey(@NotNull K key);

    /**
     * @param key K to save for debouncing
     * @return if the key was debounced
     */
    boolean debounce(@NotNull K key);

}