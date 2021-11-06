package com.gmail.virustotalop.equipment.region;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PointTest {

    @Test
    public void testCreateNullCords() {
        assertNull(Point.create(null));
    }

    @Test
    public void testCreateNoComma() {
        assertNull(Point.create(""));
    }

    @Test
    public void testCreateLengthNotThree() {
        assertNull(Point.create("32,32,32,32"));
    }

    @Test
    public void testCreateValid() {
        assertNotNull(Point.create("32,32,32"));
    }

    @Test
    public void testX() {
        int x = 1;
        int y = 2;
        int z = 3;
        Point point = new Point(x, y, z);
        assertEquals(x, point.getX());
    }

    @Test
    public void testY() {
        int x = 1;
        int y = 2;
        int z = 3;
        Point point = new Point(x, y, z);
        assertEquals(y, point.getY());
    }

    @Test
    public void testZ() {
        int x = 1;
        int y = 2;
        int z = 3;
        Point point = new Point(x, y, z);
        assertEquals(z, point.getZ());
    }
}