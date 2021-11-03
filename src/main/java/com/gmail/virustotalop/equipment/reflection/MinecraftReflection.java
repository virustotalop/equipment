package com.gmail.virustotalop.equipment.reflection;

import com.gmail.virustotalop.equipment.ClassCache;
import com.gmail.virustotalop.equipment.reflection.cache.CaffeineClassCache;
import org.bukkit.Bukkit;

import java.util.concurrent.TimeUnit;

public final class MinecraftReflection {

    public static final String VERSION = version();

    private static String version() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        String version = packageName.substring(packageName.lastIndexOf('.') + 1);
        return version;
    }


    private final ClassCache classCache;

    public MinecraftReflection() {
        this(new CaffeineClassCache(5, TimeUnit.MINUTES));
    }

    public MinecraftReflection(ClassCache classCache) {
        this.classCache = classCache;
    }

    public Class<?> getNMSClass(String nmsClassName, String... optPackages) {
        String nmsPackage = "";
        for(int i = 0; i < optPackages.length; i++) {
            nmsPackage += optPackages[i];
            if(i != optPackages.length - 1) {
                nmsPackage += ".";
            }
        }
        String basePackage = "net.minecraft";
        String legacyClassName = basePackage + ".server." + VERSION + "." + nmsClassName;;
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
        return this.getNMSClass(className, new String[0]);
    }

    public Class<?> getCraftClass(String className) {
        //TODO
        return null;
    }

    public Class<?> getCraftClass(String className, String... packages) {
        //TODO
        return null;
    }
}
