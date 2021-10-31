package com.gmail.virustotalop.equipment.reflection;

import com.gmail.virustotalop.equipment.ClassCache;
import com.gmail.virustotalop.equipment.reflection.cache.CaffeineClassCache;

import java.util.concurrent.TimeUnit;

public final class MinecraftReflection {

    private static final String VERSION = version();

    private static String version() {
        //TODO - find version
        return null;
    }



    private final ClassCache classCache;

    public MinecraftReflection() {
        this(new CaffeineClassCache(5, TimeUnit.MINUTES));
    }

    public MinecraftReflection(ClassCache classCache) {
        this.classCache = classCache;
    }

    public Class<?> getNMSClass(String className, String... packages) {
        //TODO
        return null;
    }

    public Class<?> getCraftClass(String className) {
        //TODO
        return null;
    }

    public Class<?> getCraftClass(String className, String packageName) {
        //TODO
        return null;
    }
}
