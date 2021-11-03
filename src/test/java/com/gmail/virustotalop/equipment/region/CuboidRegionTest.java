package com.gmail.virustotalop.equipment.region;

import com.gmail.virustotalop.equipment.mock.MockFactory;
import com.gmail.virustotalop.equipment.mock.MockWorld;
import org.bukkit.Location;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class CuboidRegionTest {

    @Test
    public void testIsWithin() {
        MockFactory factory = new MockFactory();
        MockWorld world = factory.mock(MockWorld.class, "test");
        Point min = Point.create("0,0,0");
        Point max = Point.create("64,64,64");
        assertEquals("test", world.getName());
        CuboidRegion region = new CuboidRegion("test", world, min, max);
        assertTrue(region.isWithin(new Location(world, 32, 32, 32)));
    }

    @Test
    public void testIsNotWithinX() {
        MockFactory factory = new MockFactory();
        MockWorld world = factory.mock(MockWorld.class, "test");
        Point min = Point.create("0,0,0");
        Point max = Point.create("64,64,64");
        assertEquals("test", world.getName());
        CuboidRegion region = new CuboidRegion("test", world, min, max);
        assertFalse(region.isWithin(new Location(world, 65, 32, 32)));
    }

    @Test
    public void testIsNotWithinY() {
        MockFactory factory = new MockFactory();
        MockWorld world = factory.mock(MockWorld.class, "test");
        Point min = Point.create("0,0,0");
        Point max = Point.create("64,64,64");
        assertEquals("test", world.getName());
        CuboidRegion region = new CuboidRegion("test", world, min, max);
        assertFalse(region.isWithin(new Location(world, 32, 65, 32)));
    }

    @Test
    public void testIsNotWithinZ() {
        MockFactory factory = new MockFactory();
        MockWorld world = factory.mock(MockWorld.class, "test");
        Point min = Point.create("0,0,0");
        Point max = Point.create("64,64,64");
        assertEquals("test", world.getName());
        CuboidRegion region = new CuboidRegion("test", world, min, max);
        assertFalse(region.isWithin(new Location(world, 32, 32, 65)));
    }
}