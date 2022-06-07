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
    public void populate(@NotNull Inventory toPopulate) {
        toPopulate.addItem(this.itemStack);
    }
}
