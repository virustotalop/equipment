package com.gmail.virustotalop.equipment.reflection.cache;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public interface ClassCache {


    @NotNull Map<String, Optional<Class<?>>> cache();

    default @Nullable Class<?> lookup(@NotNull String... classes) {
        return this.lookup(null, classes);
    }


    default @Nullable Class<?> lookup(@NotNull ClassLoader classLoader, @NotNull String... classes) {
        for(String clazzName : classes) {
            Optional<Class<?>> clazzOpt = this.cache().get(clazzName);
            if(clazzOpt != null && clazzOpt.isPresent()) {
                return clazzOpt.get();
            }
            try {
                Class<?> clazz;
                if(classLoader == null) {
                    clazz = Class.forName(clazzName);
                } else {
                    clazz = Class.forName(clazzName, true, classLoader);
                }
                clazzOpt = Optional.of(clazz);
                this.cache().put(clazzName, clazzOpt);
                return clazz;
            } catch(ClassNotFoundException e) {
                this.cache().put(clazzName, Optional.empty());
            }
        }
        return null;
    }
}