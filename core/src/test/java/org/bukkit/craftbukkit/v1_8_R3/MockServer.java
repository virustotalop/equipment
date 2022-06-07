package org.bukkit.craftbukkit.v1_8_R3;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public abstract class MockServer implements Server {

    private final Map<String, World> worlds = new HashMap<>();
    private final Logger logger = Logger.getLogger(MockServer.class.getName());
    private final List<String> commands = new ArrayList<>();

    @Override
    public World getWorld(String name) {
        return this.worlds.get(name);
    }

    public void addWorld(World world) {
        this.worlds.put(world.getName().toLowerCase(), world);
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
       return this.commands.add(commandLine);
    }

    public List<String> getCommands() {
        return this.commands;
    }
}
