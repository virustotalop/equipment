package com.gmail.virustotalop.equipment.reflection;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

public interface Reflection {

    Class<?>[] EMPTY_CLASS_AR = new Class<?>[0];

    @Nullable Class<?> getClass(String className);

    default @Nullable Method getMethodByReturnType(Class<?> searchIn, Class<?> returnType, boolean isStatic) {
        return this.getMethodByReturnType(searchIn, returnType, isStatic, EMPTY_CLASS_AR);
    }

    @Nullable Method getMethodByReturnType(Class<?> searchIn, Class<?> returnType,
                                           boolean isStatic, Class<?>... paramTypes);

}