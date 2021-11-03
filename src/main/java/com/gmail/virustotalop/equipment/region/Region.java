package com.gmail.virustotalop.equipment.region;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;

public abstract class Region {

    private final String name;
    private final String worldName;

    public Region(String name, World world) {
        this.name = name;
        this.worldName = world.getName();
    }

    public String getName() {
        return this.name;
    }

    public String getWorldName() {
        return this.worldName;
    }

    public World getWorld() {
        return Bukkit.getServer().getWorld(this.worldName);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Region)) return false;
        Region region = (Region) o;
        return Objects.equals(this.name, region.name) &&
                Objects.equals(this.worldName, region.worldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.worldName);
    }

    public abstract boolean isWithin(Location location);

}