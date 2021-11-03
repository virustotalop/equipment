package com.gmail.virustotalop.equipment.loot;

import java.util.Map;

public class LootTable {

    private final Map<Loot, Integer> table;
    private final int scale;

    public LootTable(Map<Loot, Integer> table, int scale) {
        this.table = table;
        this.scale = scale;
    }

    public boolean roll() {
        return this.roll(0);
    }

    public boolean roll(int guaranteed) {
        //TODO - implement
        return false;
    }
}
