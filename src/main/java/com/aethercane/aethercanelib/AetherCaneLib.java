package com.aethercane.aethercanelib;

import com.aethercane.aethercanelib.skin.listener.PlayerListener;
import com.aethercane.aethercanelib.skin.service.SkinService;
import org.bukkit.plugin.java.JavaPlugin;

public class AetherCaneLib extends JavaPlugin{

    private static AetherCaneLib instance;

    private final SkinService skinService = new SkinService();

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerListener(skinService), this);
    }

    @Override
    public void onDisable() {
    }

    public SkinService getSkinService() {
        return skinService;
    }

    public static AetherCaneLib getInstance() {
        return instance;
    }

}
