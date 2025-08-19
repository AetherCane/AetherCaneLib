package com.aethercane.aethercanelib.skin.listener;

import com.aethercane.aethercanelib.skin.service.SkinService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerListener implements Listener {

    private final SkinService skinService;

    public PlayerListener(SkinService skinService) {
        this.skinService = skinService;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        skinService.save(player);
    }
}
