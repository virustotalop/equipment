package com.gmail.virustotalop.equipment.inject;

import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.List;

public final class InjectUtil {

    public static <T> List<T> collect(Class<T> superClazz, Injector injector) {
        List<T> bindings = new ArrayList<>();
        injector.getAllBindings().values().forEach(binding -> {
            Class<?> bindingClazz = binding.getKey().getTypeLiteral().getRawType();
            if(superClazz.isAssignableFrom(bindingClazz)) {
                bindings.add((T) binding.getProvider().get());
            }
        });
        return bindings;
    }

    private InjectUtil() {
    }
}