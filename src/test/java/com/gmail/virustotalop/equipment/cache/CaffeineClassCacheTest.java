package com.gmail.virustotalop.equipment.cache;

import com.gmail.virustotalop.equipment.ClassCache;
import com.gmail.virustotalop.equipment.reflection.cache.CaffeineClassCache;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CaffeineClassCacheTest {

    @Test
    public void testInCache() {
        String testClassName = "net.minecraft.server.v1_8_R3.TestNMSLegacy";
        ClassCache cache = new CaffeineClassCache(10, TimeUnit.MINUTES);
        Class<?> testClazz = cache.lookup(testClassName);
        assertNotNull(testClazz);
        assertEquals(cache.cache().get(testClassName).get(), testClazz);
    }

    @Test
    public void testNonExistent() {
        String testClassName = "NonExistentClass";
        ClassCache cache = new CaffeineClassCache(10, TimeUnit.MINUTES);
        Class<?> testClazz = cache.lookup(testClassName);
        assertNull(testClazz);
        assertTrue(cache.cache().get(testClassName).isEmpty());
    }

    @Test
    public void testCached() {
        String testClassName = "net.minecraft.v1_8_R3.Test";
        ClassCache cache = new CaffeineClassCache(10, TimeUnit.MINUTES);
        Class<?> testClazz = cache.lookup(testClassName);
        Class<?> cachedTestClazz = cache.lookup(testClassName);
        assertEquals(testClazz, cachedTestClazz);
    }
}