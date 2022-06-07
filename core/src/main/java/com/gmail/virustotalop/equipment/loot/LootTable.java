package com.gmail.virustotalop.equipment.loot;

import java.util.Collection;

public interface LootTable<T extends Loot> {

    default Collection<T> roll() {
        return this.roll(0);
    }

    Collection<T> roll(int guaranteed);

}