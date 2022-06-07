package com.gmail.virustotalop.equipment.reflection;


import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class JavaReflection implements Reflection {

    @Override
    public @Nullable Method getMethodByReturnType(Class<?> searchIn, Class<?> returnType, boolean isStatic) {
        for (Method method : searchIn.getDeclaredMethods()) {
            if (!isStatic || Modifier.isStatic(method.getModifiers())) {
                if (method.getReturnType().equals(returnType)) {
                    return method;
                }
            }
        }
        return null;
    }
}