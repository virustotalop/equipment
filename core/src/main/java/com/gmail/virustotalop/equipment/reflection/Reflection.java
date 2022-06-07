package com.gmail.virustotalop.equipment.reflection;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

public interface Reflection {

    @Nullable Method getMethodByReturnType(Class<?> searchIn, Class<?> returnType, boolean isStatic);

}