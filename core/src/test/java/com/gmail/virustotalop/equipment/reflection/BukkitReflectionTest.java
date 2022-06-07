package com.gmail.virustotalop.equipment.reflection;

import com.gmail.virustotalop.equipment.mock.MockFactory;
import net.minecraft.TestNMSModern;
import net.minecraft.test.PackagedNMSModern;
import net.minecraft.test.child.ChildPackagedNMSModern;
import net.minecraft.server.v1_8_R3.TestNMSLegacy;
import org.bukkit.craftbukkit.v1_8_R3.TestCraft;
import org.bukkit.craftbukkit.v1_8_R3.test.TestCraftPackaged;
import org.bukkit.craftbukkit.v1_8_R3.test.child.ChildTestCraftPackaged;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class BukkitReflectionTest {

    private MinecraftReflection reflect;

    @BeforeEach
    public void setup() {
        MockFactory factory = new MockFactory();
        factory.createServer();
        this.reflect = new BukkitReflection();
    }

    @Test
    public void testVersion() {
        assertEquals("v1_8_R3", BukkitReflection.VERSION);
    }

    @Test
    public void lookupNmsClassLegacy() {
        assertEquals(TestNMSLegacy.class, this.reflect.getNMSClass("TestNMSLegacy"));
    }

    @Test
    public void lookupNmsClassModern() {
        assertEquals(TestNMSModern.class, this.reflect.getNMSClass("TestNMSModern"));
    }


    @Test
    public void lookupNmsClassModernPackaged() {
        assertEquals(PackagedNMSModern.class, this.reflect.getNMSClass("PackagedNMSModern", "test"));
    }

    @Test
    public void lookupNmsClassModernPackagedChild() {
        assertEquals(ChildPackagedNMSModern.class, this.reflect.getNMSClass("ChildPackagedNMSModern",
                "test",
                "child"));
    }

    @Test
    public void lookupCraftClassNoPackage() {
        assertEquals(TestCraft.class, this.reflect.getCraftClass("TestCraft"));
    }

    @Test
    public void lookupCraftClassOnePackageDeep() {
        assertEquals(TestCraftPackaged.class, this.reflect.getCraftClass("TestCraftPackaged",
                "test"));
    }

    @Test
    public void lookupCraftClassTwoPackagesDeep() {
        assertEquals(ChildTestCraftPackaged.class, this.reflect.getCraftClass("ChildTestCraftPackaged",
                "test", "child"));
    }
}
