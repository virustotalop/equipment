package com.gmail.virustotalop.equipment.region;

import com.gmail.virustotalop.equipment.mock.MockFactory;
import com.gmail.virustotalop.equipment.mock.MockServer;
import com.gmail.virustotalop.equipment.mock.MockWorld;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class RegionTest {

    @Test
    public void testGetName() {
        String worldName = "test";
        String regionName = "test";
        MockFactory factory = new MockFactory();
        MockWorld world = factory.mock(MockWorld.class, worldName);
        Region region = factory.mock(Region.class, regionName, world);
        assertEquals(regionName, region.getName());
    }

    @Test
    public void testGetWorldName() {
        String worldName = "test";
        MockFactory factory = new MockFactory();
        MockWorld world = factory.mock(MockWorld.class, worldName);
        Region region = factory.mock(Region.class, "test", world);
        assertEquals(worldName, region.getWorldName());
    }

    @Test
    public void testGetWorld() {
        String worldName = "test";
        MockFactory factory = new MockFactory();
        MockWorld world = factory.mock(MockWorld.class, worldName);
        MockServer server = factory.createServer();
        server.addWorld(world);
        Region region = factory.mock(Region.class, "test", world);
        assertEquals(world, region.getWorld());
    }
}
