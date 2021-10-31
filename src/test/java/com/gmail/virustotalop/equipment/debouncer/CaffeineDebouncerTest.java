package com.gmail.virustotalop.equipment.debouncer;

import com.gmail.virustotalop.equipment.Debouncer;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CaffeineDebouncerTest {

    @Test
    public void testDebounce() {
        Debouncer<UUID> debouncer = new CaffeineDebouncer<>(1, TimeUnit.SECONDS);
        UUID uuid = UUID.randomUUID();
        assertFalse(debouncer.debounce(uuid));
        try {
            Thread.sleep(1050);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(debouncer.wouldDebounce(uuid));
    }

    @Test
    public void testIsDebouncedTrue() {
        Debouncer<UUID> debouncer = new CaffeineDebouncer<>(1, TimeUnit.SECONDS);
        UUID uuid = UUID.randomUUID();
        assertFalse(debouncer.debounce(uuid));
        assertTrue(debouncer.wouldDebounce(uuid));
    }

    @Test
    public void testIsDebouncedFalse() {
        Debouncer<UUID> debouncer = new CaffeineDebouncer<>(1, TimeUnit.SECONDS);
        assertFalse(debouncer.wouldDebounce(UUID.randomUUID()));
    }
}
