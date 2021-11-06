package com.gmail.virustotalop.equipment.replacer;

import org.bukkit.entity.Player;

public class DefaultReplacers implements Replacers {

    @Override
    public String replace(Player player, String replaceIn) {
        return replaceIn.replace("%player%", player.getName());
    }
}