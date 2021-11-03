package com.gmail.virustotalop.equipment.reflection;

import com.gmail.virustotalop.equipment.mock.MockFactory;
import net.minecraft.TestNMSModern;
import net.minecraft.test.PackagedNMSModern;
import net.minecraft.test.child.ChildPackagedNMSModern;
import net.minecraft.v1_8_R3.TestNMSLegacy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class MinecraftReflectionTest {

    @BeforeEach
    public void setup() {
        MockFactory factory = new MockFactory();
        factory.createServer();
    }

    @Test
    public void testVersion() {
        assertEquals("v1_8_R3", MinecraftReflection.VERSION);
    }

    @Test
    public void lookupNmsClassLegacy() {
        MinecraftReflection reflect = new MinecraftReflection();
        assertEquals(TestNMSLegacy.class, reflect.getNMSClass("TestNMSLegacy"));
    }

    @Test
    public void lookupNmsClassModern() {
        MinecraftReflection reflect = new MinecraftReflection();
        assertEquals(TestNMSModern.class, reflect.getNMSClass("TestNMSModern"));
    }


    @Test
    public void lookupNmsClassModernPackaged() {
        MinecraftReflection reflect = new MinecraftReflection();
        assertEquals(PackagedNMSModern.class, reflect.getNMSClass("PackagedNMSModern", "test"));
    }

    @Test
    public void lookupNmsClassModernPackagedChild() {
        MinecraftReflection reflect = new MinecraftReflection();
        assertEquals(ChildPackagedNMSModern.class, reflect.getNMSClass("ChildPackagedNMSModern",
                "test",
                "child"));
    }
}
