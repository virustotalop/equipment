package com.gmail.virustotalop.equipment.region;

import org.bukkit.Location;
import org.bukkit.World;

public class GlobalRegion extends Region {

    public GlobalRegion(String name, World world) {
        super(name, world);
    }

    @Override
    public boolean isWithin(Location location) {
        return location.getWorld().getName().equals(this.getWorldName());
    }
}