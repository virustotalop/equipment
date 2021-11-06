package com.gmail.virustotalop.equipment.region;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class GlobalRegion extends Region {

    public GlobalRegion(@NotNull String name, @NotNull World world) {
        super(name, world);
    }

    @Override
    public boolean isWithin(@NotNull Location location) {
        return location.getWorld().getName().equals(this.getWorldName());
    }
}