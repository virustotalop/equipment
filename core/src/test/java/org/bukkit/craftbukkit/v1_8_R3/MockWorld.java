package org.bukkit.craftbukkit.v1_8_R3;

import org.bukkit.World;

public abstract class MockWorld implements World {

    private final String name;

    public MockWorld(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}