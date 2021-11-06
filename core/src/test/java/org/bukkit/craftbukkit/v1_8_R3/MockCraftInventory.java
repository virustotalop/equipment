package org.bukkit.craftbukkit.v1_8_R3;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class MockCraftInventory implements Inventory {

    private Map<Integer, ItemStack> slots = new HashMap<>();

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public void setMaxStackSize(int size) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.slots.get(index);
    }

    @Override
    public void setItem(int index, ItemStack item) {
        if(index < 0 || index >= this.getSize()) {
            return;
        }
        this.slots.put(index, item);
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        HashMap<Integer, ItemStack> overflow = new HashMap<>();
        for(ItemStack item : items) {
            for(int i = 0; i < this.getSize(); i++) {
                ItemStack stack = this.slots.get(i);
                if(stack == null) {
                    this.slots.put(i, item);
                    break;
                } else if(i == this.getSize() - 1) {
                    overflow.put(item.getAmount(), item);
                }
            }
        }
        return overflow;
    }

    @Override
    public ItemStack[] getContents() {
        ItemStack[] contents = new ItemStack[this.getSize()];
        for(int i = 0; i < this.getSize(); i++) {
            contents[i] = this.getItem(i);
        }
        return contents;
    }


    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean contains(ItemStack item) {
        return false;
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {

    }

    @Override
    public void remove(ItemStack item) {

    }

    @Override
    public void clear(int index) {

    }

    @Override
    public void clear() {

    }
}
