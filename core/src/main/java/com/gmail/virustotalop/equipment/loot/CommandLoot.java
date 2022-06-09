package com.gmail.virustotalop.equipment.loot;

import com.gmail.virustotalop.equipment.replacer.DefaultReplacers;
import com.gmail.virustotalop.equipment.replacer.Replacers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandLoot implements Loot<Player> {

    private final String command;
    private final Replacers replacers;

    public CommandLoot(@NotNull String command) {
        this(command, new DefaultReplacers());
    }

    public CommandLoot(@NotNull String command, @NotNull Replacers replacers) {
        this.command = command;
        this.replacers = replacers;
    }

    @Override
    public boolean populate(@NotNull Player toPopulate) {
        String replacedCommand = this.replacers.replace(toPopulate, this.command);
        return Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), replacedCommand);
    }
}
