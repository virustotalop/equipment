package com.gmail.virustotalop.equipment;

public interface Debouncer<K> {

    /**
     * @param key K to check if the key would debounce
     * @return if the key was debounced
     */
    boolean wouldDebounce(K key);

    /**
     * @param key K to save for debouncing
     * @return if the key was debounced
     */
    boolean debounce(K key);

}