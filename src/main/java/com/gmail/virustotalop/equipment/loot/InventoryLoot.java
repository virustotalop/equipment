package com.gmail.virustotalop.equipment.loot;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryLoot implements Loot<Inventory> {

    private final ItemStack itemStack;

    public InventoryLoot(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public void populate(Inventory populate) {
        populate.addItem(this.itemStack);
    }
}
