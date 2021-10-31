package com.gmail.virustotalop.equipment;

public interface Debouncer<K> {

    /**
     * @param key K to check if the key will debounced
     * @return if the key was debounced
     */
    boolean wouldDebounce(K key);

    /**
     * @param key K to save the key for debouncing
     * @return if the key was debounced
     */
    boolean debounce(K key);

}