package com.gmail.virustotalop.equipment.mock;

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