package com.gmail.virustotalop.equipment.replacer;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface Replacers {

    @NotNull String replace(@NotNull Player player, @NotNull String replaceIn);

}