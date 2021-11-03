package com.gmail.virustotalop.equipment.region;

import com.gmail.virustotalop.equipment.mock.MockFactory;
import org.bukkit.craftbukkit.v1_8_R3.MockWorld;
import org.bukkit.Location;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GlobalRegionTest {

    @Test
    public void testIsWithin() {
        MockFactory factory = new MockFactory();
        MockWorld world = factory.mock(MockWorld.class, "test");
        assertEquals("test", world.getName());
        GlobalRegion region = new GlobalRegion("test", world);
        assertTrue(region.isWithin(new Location(world, 32, 32, 32)));
    }
}
