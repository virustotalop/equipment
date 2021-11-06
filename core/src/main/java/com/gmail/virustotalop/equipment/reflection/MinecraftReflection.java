package com.gmail.virustotalop.equipment.reflection;

import com.gmail.virustotalop.equipment.reflection.cache.ClassCache;
import com.gmail.virustotalop.equipment.reflection.cache.CaffeineClassCache;
import org.bukkit.Bukkit;

import java.util.concurrent.TimeUnit;

public final class MinecraftReflection {

    private static final String[] EMPTY_ARR = new String[0];
    public static final String VERSION = version();

    private static String version() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    private final ClassCache classCache;

    public MinecraftReflection() {
        this(new CaffeineClassCache(5, TimeUnit.MINUTES));
    }

    public MinecraftReflection(ClassCache classCache) {
        this.classCache = classCache;
    }

    public Class<?> getNMSClass(String nmsClassName, String... optPackages) {
        StringBuilder nmsPackage = new StringBuilder();
        for(int i = 0; i < optPackages.length; i++) {
            nmsPackage.append(optPackages[i]);
            if(i != optPackages.length - 1) {
                nmsPackage.append(".");
            }
        }
        String basePackage = "net.minecraft";
        String legacyClassName = basePackage + ".server." + VERSION + "." + nmsClassName;
        String modernClassName = basePackage;
        if(nmsPackage.length() > 0) {
            modernClassName += ".";
        }
        modernClassName += nmsPackage + "." + nmsClassName;
        Class<?> legacyClass = this.classCache.lookup(legacyClassName);
        if(legacyClass == null) {
            return this.classCache.lookup(modernClassName);
        }
        return legacyClass;
    }

    public Class<?> getNMSClass(String className) {
        return this.getNMSClass(className, EMPTY_ARR);
    }

    public Class<?> getCraftClass(String className) {
        return this.getCraftClass(className, EMPTY_ARR);
    }

    public Class<?> getCraftClass(String className, String... optPackages) {
        StringBuilder craftClassName = new StringBuilder("org.bukkit.craftbukkit." + VERSION);
        if(optPackages.length > 0) {
            craftClassName.append(".");
            for(int i = 0; i < optPackages.length; i++) {
                craftClassName.append(optPackages[i]);
                if(i != optPackages.length - 1) {
                    craftClassName.append(".");
                }
            }
        }
        craftClassName.append(".").append(className);
        return this.classCache.lookup(craftClassName.toString());
    }
}
