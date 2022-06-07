package com.gmail.virustotalop.equipment.region;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class Region {

    private final String name;
    private final String worldName;

    public Region(@NotNull String name, @NotNull World world) {
        this.name = name;
        this.worldName = world.getName();
    }

    /**
     * @return the name of the region
     */
    public @NotNull String getName() {
        return this.name;
    }

    /**
     * @return the name of the world that the region is located in
     */
    public @NotNull String getWorldName() {
        return this.worldName;
    }

    /**
     * @return the world that the region is located in
     */
    public @Nullable World getWorld() {
        return Bukkit.getServer().getWorld(this.worldName);
    }

    /**
     * @param location Location to check if the key would debounce
     * @return if the location is within the region
     */
    public abstract boolean isWithin(Location location);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        Region region = (Region) o;
        return Objects.equals(this.name, region.name) &&
                Objects.equals(this.worldName, region.worldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.worldName);
    }
}