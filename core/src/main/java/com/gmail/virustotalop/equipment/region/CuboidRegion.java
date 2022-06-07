package com.gmail.virustotalop.equipment.region;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class CuboidRegion extends Region {

    private final int minX;
    private final int minY;
    private final int minZ;
    private final int maxX;
    private final int maxY;
    private final int maxZ;

    public CuboidRegion(@NotNull String name, @NotNull World world, @NotNull Point min, @NotNull Point max) {
        super(name, world);
        this.maxX = Math.max(min.getX(), max.getX());
        this.maxY = Math.max(min.getY(), max.getY());
        this.maxZ = Math.max(min.getZ(), max.getZ());
        this.minX = Math.min(min.getX(), max.getX());
        this.minY = Math.min(min.getY(), max.getY());
        this.minZ = Math.min(min.getZ(), max.getZ());
    }

    @Override
    public boolean isWithin(@NotNull Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        if (x > this.maxX || x < this.minX) {
            return false;
        } else if (y > this.maxY || y < this.minY) {
            return false;
        } else if (z > this.maxZ || z < this.minZ) {
            return false;
        }
        return location.getWorld().getName().equals(this.getWorldName());
    }
}