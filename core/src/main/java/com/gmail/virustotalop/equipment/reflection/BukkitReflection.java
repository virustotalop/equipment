package com.gmail.virustotalop.equipment.reflection;

import com.gmail.virustotalop.equipment.reflection.cache.ClassCache;
import com.gmail.virustotalop.equipment.reflection.cache.CaffeineClassCache;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

public class BukkitReflection extends JavaReflection implements MinecraftReflection {

    public static final String VERSION = version();

    private static String version() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    private final ClassCache classCache;

    public BukkitReflection() {
        this(new CaffeineClassCache(5, TimeUnit.MINUTES));
    }

    public BukkitReflection(@NotNull ClassCache classCache) {
        this.classCache = classCache;
    }

    @Override
    public @Nullable Class<?> getNMSClass(@NotNull String nmsClassName, @NotNull String... optPackages) {
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

    @Override
    public @Nullable Class<?> getCraftClass(@NotNull String className, @NotNull String... optPackages) {
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
