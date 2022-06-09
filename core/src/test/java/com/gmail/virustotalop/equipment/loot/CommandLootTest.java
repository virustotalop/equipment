package com.gmail.virustotalop.equipment.loot;

import com.gmail.virustotalop.equipment.mock.MockFactory;
import org.bukkit.craftbukkit.v1_8_R3.MockPlayer;
import org.bukkit.craftbukkit.v1_8_R3.MockServer;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CommandLootTest {

    @Test
    public void testPopulate() {
        MockFactory factory = new MockFactory();
        MockServer server = factory.createServer();
        String playerName = "test";
        Player player = factory.mock(MockPlayer.class, playerName);
        String commandStr = "some command";
        Loot<Player> loot = new CommandLoot(commandStr);
        assertTrue(loot.populate(player));
        List<String> commands = server.getCommands();
        assertEquals(commandStr, commands.get(0));
    }

    @Test
    public void testPopulateWithReplacers() {
        MockFactory factory = new MockFactory();
        MockServer server = factory.createServer();
        String playerName = "test";
        Player player = factory.mock(MockPlayer.class, playerName);
        String commandStr = "some command %player%";
        String replacedStr = commandStr.replace("%player%", playerName);
        Loot<Player> loot = new CommandLoot(commandStr);
        assertTrue(loot.populate(player));
        List<String> commands = server.getCommands();
        assertEquals(replacedStr, commands.get(0));
    }
}