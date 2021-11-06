package com.gmail.virustotalop.equipment.reflection.cache;

import java.util.Map;
import java.util.Optional;

public interface ClassCache {

    Map<String, Optional<Class<?>>> cache();

    default Class<?> lookup(String... classes) {
        return this.lookup(null, classes);
    }

    default Class<?> lookup(ClassLoader classLoader, String... classes) {
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