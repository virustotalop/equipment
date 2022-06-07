package com.gmail.virustotalop.equipment.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MinecraftReflection extends Reflection {

    String[] EMPTY_ARR = new String[0];

    @Nullable Class<?> getNMSClass(@NotNull String nmsClassName, @NotNull String... optPackages);

    default @Nullable Class<?> getNMSClass(@NotNull String className) {
        return this.getNMSClass(className, EMPTY_ARR);
    }

    default @Nullable Class<?> getCraftClass(@NotNull String className) {
        return this.getCraftClass(className, EMPTY_ARR);
    }

    @Nullable Class<?> getCraftClass(@NotNull String className, @NotNull String... optPackages);

}