package com.gmail.virustotalop.equipment.replacer;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DefaultReplacers implements Replacers {

    @Override
    public @NotNull String replace(@NotNull Player player, @NotNull String replaceIn) {
        return replaceIn.replace("%player%", player.getName());
    }
}