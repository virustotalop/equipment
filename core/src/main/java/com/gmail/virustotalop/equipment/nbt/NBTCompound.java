package com.gmail.virustotalop.equipment.nbt;

import com.gmail.virustotalop.equipment.reflection.BukkitReflection;
import com.gmail.virustotalop.equipment.reflection.MinecraftReflection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class NBTCompound {

    private static final MinecraftReflection REFLECT = new BukkitReflection();
    private static final Class<?> NMS_ITEM_STACK_CLASS = REFLECT.getNMSClass(
            "ItemStack",
            "world",
            "item"
    );
    private static final Class<?> CRAFT_ITEM_STACK_CLASS = REFLECT.getCraftClass(
            "CraftItemStack",
            "inventory"
    );
    private static final Class<?> COMPOUND_CLASS = REFLECT.getNMSClass("NBTTagCompound", "nbt");
    private static final Class<?> PARSER_CLASS = REFLECT.getNMSClass("MojangsonParser", "nbt");
    private static final Class<?> TAG_CLASS = REFLECT.getNMSClass("NBTBase", "nbt");

    private static final Method PARSE = REFLECT.getMethodByReturnType(
            PARSER_CLASS,
            COMPOUND_CLASS,
            true
    );
    private static final Method GET_KEYS = REFLECT.getMethodByReturnType(
            COMPOUND_CLASS,
            Set.class,
            true
    );
    private static final Method GET = REFLECT.getMethodByReturnType(
            COMPOUND_CLASS,
            TAG_CLASS,
            true
    );
    private static final Method AS_CRAFT_COPY = REFLECT.getMethodByReturnType(
            CRAFT_ITEM_STACK_CLASS,
            CRAFT_ITEM_STACK_CLASS,
            true,
            ItemStack.class
    );
    private static final Method AS_NMS_COPY = REFLECT.getMethodByReturnType(
            CRAFT_ITEM_STACK_CLASS,
            NMS_ITEM_STACK_CLASS,
            true,
            ItemStack.class
    );
    private static final Method GET_COMPOUND_TAG = REFLECT.getMethodByReturnType(
            NMS_ITEM_STACK_CLASS,
            COMPOUND_CLASS,
            false
    );

    public static boolean isCompound(@NotNull Object compound) {
        if (compound == null) {
            return false;
        }
        return compound.getClass().equals(COMPOUND_CLASS);
    }

    public static boolean fuzzyMatches(@NotNull NBTCompound compare, @NotNull NBTCompound compound) {
        for (String key : compare.getKeys()) {
            Object get = compound.get(key);
            if (get == null) {
                return false;
            } else if (isCompound(get)) {
                Object compareCompound = compare.get(key);
                if (!isCompound(compareCompound)) {
                    return false;
                }
                return fuzzyMatches(new NBTCompound(compareCompound), new NBTCompound(get));
            } else if (!compare.get(key).equals(compound.get(key))) {
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

    public Set<String> getKeys() {
        try {
            return (Set<String>) GET_KEYS.invoke(this.inner);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasKey(String key) {
        return this.getKeys().contains(key);
    }

    public Object get(String key) {
        try {
            if (!this.hasKey(key)) {
                return null;
            }
            return GET.invoke(this.inner, key);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object parseNBTCompoundFromJson(String json) throws Exception {
        return PARSE.invoke(null, json);
    }

    private Object retrieveNBTCompoundFromItem(ItemStack itemStack) {
        try {
            Object craftCopy = AS_CRAFT_COPY.invoke(null, itemStack);
            Object nmsStack = AS_NMS_COPY.invoke(null, craftCopy);
            return GET_COMPOUND_TAG.invoke(nmsStack);
        } catch (Exception e) {
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
