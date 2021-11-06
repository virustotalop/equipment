package com.gmail.virustotalop.equipment.mock;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.MockServer;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class MockFactory {

    public <T> T mock(Class<T> mockClazz) {
        return Mockito.mock(mockClazz, Mockito.withSettings()
                .useConstructor()
                .defaultAnswer(Mockito.CALLS_REAL_METHODS));
    }

    public <T> T mock(Class<T> mockClazz, Object... args) {
        return Mockito.mock(mockClazz, Mockito.withSettings().useConstructor(args)
                .defaultAnswer(Mockito.CALLS_REAL_METHODS));
    }

    public MockServer createServer() {
        MockServer server = this.mock(MockServer.class);
        try {
            Field serverField = Bukkit.class.getDeclaredField("server");
            serverField.setAccessible(true);
            serverField.set(null, server);
            return (MockServer) Bukkit.getServer();
        } catch(NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
