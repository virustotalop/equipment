package com.gmail.virustotalop.equipment.loot;

import com.gmail.virustotalop.equipment.mock.MockFactory;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.MockCraftInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class InventoryLootTest {

    @Test
    public void testPopulate() {
        MockFactory factory = new MockFactory();
        Inventory inventory = factory.mock(MockCraftInventory.class);
        ItemStack dirt = new ItemStack(Material.DIRT);
        Loot<Inventory> loot = new InventoryLoot(dirt);
        loot.populate(inventory);
        assertEquals(dirt, inventory.getContents()[0]);
    }
}