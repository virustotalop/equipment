package com.gmail.virustotalop.equipment.reflection;

import com.gmail.virustotalop.equipment.mock.MockFactory;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class MinecraftReflectionTest {

    @Test
    public void testVersion() {
        MockFactory factory = new MockFactory();
        factory.createServer();
        assertEquals("v1_8_R3", MinecraftReflection.VERSION);
    }
}
