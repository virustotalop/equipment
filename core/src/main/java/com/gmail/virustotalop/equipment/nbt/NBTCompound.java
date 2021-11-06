package com.gmail.virustotalop.equipment.nbt;

import com.gmail.virustotalop.equipment.reflection.MinecraftReflection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class NBTCompound {

    private static final MinecraftReflection REFLECT = new MinecraftReflection();
    private static final Class<?> COMPOUND_CLASS = REFLECT.getNMSClass("NBTTagCompound", "nbt");
    private static final Class<?> PARSER_CLASS = REFLECT.getNMSClass("MojangsonParser", "nbt");

    private static Method parse;
    private static Method getKeys;
    private static Method get;

    static {
        try {
            parse = PARSER_CLASS.getDeclaredMethod("parse", String.class);
            get = COMPOUND_CLASS.getDeclaredMethod("get", String.class);
            for(Method method : COMPOUND_CLASS.getDeclaredMethods()) {
                if(method.getReturnType().equals(Set.class)) {
                    getKeys = method;
                    break;
                }
            }
        } catch(NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static boolean isCompound(@NotNull Object compound) {
        if(compound == null) {
            return false;
        }
        return compound.getClass().equals(COMPOUND_CLASS);
    }

    public static boolean fuzzyMatches(@NotNull NBTCompound compare, @NotNull NBTCompound compound) {
        for(String key : compare.getKeys()) {
            Object get = compound.get(key);
            if(get == null) {
                return false;
            } else if(isCompound(get)) {
                Object compareCompound = compare.get(key);
                if(!isCompound(compareCompound)) {
                    return false;
                }
                return fuzzyMatches(new NBTCompound(compareCompound), new NBTCompound(get));
            } else if(!compare.get(key).equals(compound.get(key))) {
                return false;
            }
        }
        return true;
    }

    private final Object inner;

    public NBTCompound(@NotNull Object compound) {
        this.inner = isCompound(compound) ? compound : null;
    }

    public NBTCompound(@NotNull String json) throws Exception {
        this.inner = this.parseNBTCompoundFromJson(json);
    }

    public NBTCompound(@NotNull ItemStack itemStack) {
        this.inner = this.retrieveNBTCompoundFromItem(itemStack);
    }

    public @Nullable Object getNMSCompound() {
        return this.inner;
    }

    public @Nullable Set<String> getKeys() {
        try {
            return (Set<String>) getKeys.invoke(this.inner);
        } catch(InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasKey(@NotNull String key) {
        return this.getKeys().contains(key);
    }

    public @Nullable Object get(@NotNull String key) {
        try {
            if(!this.hasKey(key)) {
                return null;
            }
            return get.invoke(this.inner, key);
        } catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object parseNBTCompoundFromJson(String json) throws Exception {
        return parse.invoke(null, json);
    }

    private Object retrieveNBTCompoundFromItem(ItemStack itemStack) {
        try {
            Class<?> craftItemStack = Class.forName("org.bukkit.craftbukkit." + MinecraftReflection.VERSION + ".inventory.CraftItemStack");
            Method asCraftCopy = craftItemStack.getMethod("asCraftCopy", ItemStack.class);
            Method asNMSCopy = craftItemStack.getMethod("asNMSCopy", ItemStack.class);
            Object craftCopy = asCraftCopy.invoke(null, itemStack);
            Object nmsStack = asNMSCopy.invoke(null, craftCopy);
            Method tagField = nmsStack.getClass().getMethod("getTag");
            return tagField.invoke(nmsStack);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "NBTCompound{" +
                "inner=" + this.inner +
                '}';
    }
}
