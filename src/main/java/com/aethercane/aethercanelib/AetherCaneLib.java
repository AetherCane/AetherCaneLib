package com.aethercane.aethercanelib;

import org.bukkit.plugin.java.JavaPlugin;

public class AetherCaneLib {

    private static JavaPlugin plugin;

    public static void register(JavaPlugin plugin) {
        AetherCaneLib.plugin = plugin;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
