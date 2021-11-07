package com.gmail.virustotalop.equipment.replacer;

import com.gmail.virustotalop.equipment.mock.MockFactory;
import org.bukkit.craftbukkit.v1_8_R3.MockPlayer;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultReplacersTest {

    @Test
    public void testReplacePlayer() {
        MockFactory factory = new MockFactory();
        Replacers replacers = new DefaultReplacers();
        String playerName = "test";
        Player player = factory.mock(MockPlayer.class, playerName);
        String toReplace = "%player%";
        String replaced = replacers.replace(player, toReplace);
        assertNotNull(replaced);
        assertEquals(playerName, replaced);
    }

    @Test
    public void testReplaceUUID() {
        MockFactory factory = new MockFactory();
        Replacers replacers = new DefaultReplacers();
        String playerName = "test";
        Player player = factory.mock(MockPlayer.class, playerName);
        UUID uuid = player.getUniqueId();
        String toReplace = "%uuid%";
        String replaced = replacers.replace(player, toReplace);
        assertNotNull(replaced);
        assertEquals(uuid.toString(), replaced);
    }
}