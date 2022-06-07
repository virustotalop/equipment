package com.gmail.virustotalop.equipment.loot;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WeightedLootTableTest {

    @Test
    public void testRoll() {
        String command = "test";
        CommandLoot loot = new CommandLoot(command);
        Map<CommandLoot, Integer> lootMap = new HashMap<>();
        lootMap.put(loot, 100);
        LootTable<CommandLoot> table = new WeightedLootTable<>(lootMap);
        Collection<CommandLoot> rolled = table.roll();
        assertEquals(1, rolled.size());
    }


}
