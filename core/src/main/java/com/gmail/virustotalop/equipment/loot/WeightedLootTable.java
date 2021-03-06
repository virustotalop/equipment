package com.gmail.virustotalop.equipment.loot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeightedLootTable<T extends Loot> implements LootTable<T> {

    public static final int DEFAULT_SCALE = 100;

    private final Map<Integer, Map.Entry<T, Integer>> table;
    private final int scale;

    public WeightedLootTable(@NotNull Map<T, Integer> table) {
        this(table, DEFAULT_SCALE);
    }

    public WeightedLootTable(@NotNull Map<T, Integer> table, int scale) {
        this.table = this.loadLoot(table);
        this.scale = scale;
    }

    private Map<Integer, Map.Entry<T, Integer>> loadLoot(Map<T, Integer> table) {
        Map<Integer, Map.Entry<T, Integer>> indexedMap = new HashMap<>();
        int index = 0;
        for (Map.Entry<T, Integer> next : table.entrySet()) {
            indexedMap.put(index, next);
            index += 1;
        }
        return indexedMap;
    }

    @Override
    public Collection<T> roll(int guaranteed) {
        return this.roll(guaranteed, 0);
    }

    private Collection<T> roll(int guaranteed, int generatedRolls) {
        Collection<T> loot = new ArrayList<>();
        boolean firstRoll = true; //For the roll() method there is no guaranteed rolls, but we want to roll once
        while (generatedRolls < guaranteed || firstRoll) {
            firstRoll = false;
            Random rand = new Random();
            T rolled = this.rollIndex(rand.nextInt(this.table.size()));
            if (rolled != null) {
                loot.add(rolled);
                generatedRolls += 1;
            }
        }
        return loot;
    }

    private T rollIndex(int index) {
        Map.Entry<T, Integer> entry = this.table.get(index);
        Random rand = new Random();
        int next = rand.nextInt(this.getScale()) + 1;
        if (next <= entry.getValue()) {
            return entry.getKey();
        }
        return null;
    }

    public int getScale() {
        return this.scale;
    }
}