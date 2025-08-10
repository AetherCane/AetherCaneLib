package com.aethercane.aethercanelib;

import org.bukkit.plugin.java.JavaPlugin;

public class AetherCaneLib extends JavaPlugin{

    private static JavaPlugin plugin;

    public static void register(JavaPlugin plugin) {
        AetherCaneLib.plugin = plugin;
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

}
