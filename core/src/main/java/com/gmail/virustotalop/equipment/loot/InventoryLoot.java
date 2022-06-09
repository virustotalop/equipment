package com.gmail.virustotalop.equipment.loot;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InventoryLoot implements Loot<Inventory> {

    private final ItemStack itemStack;

    public InventoryLoot(@NotNull ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public boolean populate(@NotNull Inventory toPopulate) {
        return toPopulate.addItem(this.itemStack).size() == 0;
    }
}