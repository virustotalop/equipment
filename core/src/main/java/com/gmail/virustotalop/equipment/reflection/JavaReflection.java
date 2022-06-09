package com.gmail.virustotalop.equipment.reflection;


import com.gmail.virustotalop.equipment.reflection.cache.CaffeineClassCache;
import com.gmail.virustotalop.equipment.reflection.cache.ClassCache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class JavaReflection implements Reflection {

    private final ClassCache classCache;

    public JavaReflection() {
        this(new CaffeineClassCache(5, TimeUnit.MINUTES));
    }

    public JavaReflection(@NotNull ClassCache classCache) {
        this.classCache = classCache;
    }

    @Override
    public @Nullable Class<?> getClass(String className) {
        return this.classCache.lookup(className);
    }

    @Override
    public @Nullable Method getMethodByReturnType(Class<?> searchIn, Class<?> returnType,
                                                  boolean isStatic, Class<?>... paramTypes) {
        for (Method method : searchIn.getDeclaredMethods()) {
            if (!isStatic || Modifier.isStatic(method.getModifiers())) {
                if (method.getReturnType().equals(returnType) && Arrays.equals(method.getParameters(), paramTypes)) {
                    return method;
                }
            }
        }
        return null;
    }
}