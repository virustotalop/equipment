package com.gmail.virustotalop.equipment.replacer;

import org.bukkit.entity.Player;

public class DefaultReplacers implements Replacers {
    @Override
    public String replace(Player populateIn, String replaceIn) {
        return replaceIn.replace("%player%", populateIn.getName());
    }
}
