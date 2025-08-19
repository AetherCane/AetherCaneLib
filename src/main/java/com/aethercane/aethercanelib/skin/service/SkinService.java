package com.aethercane.aethercanelib.skin.service;

import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SkinService {

    private static final boolean isPaper;

    private final Map<String, String> textures = new HashMap<>();

    static {
        isPaper = hasClass("com.destroystokyo.paper.PaperConfig")
                || hasClass("io.papermc.paper.configuration.Configuration");
    }

    public void save(Player player) {
        String texture = getTexture(player);
        if(texture != null)
            textures.put(player.getName(), texture);
    }

    public String getTexture(String name) {
        return textures.getOrDefault(name, "");
    }

    private String getTexture(Player player) {
        if (!isPaper) return "";
        return player.getPlayerProfile().getProperties()
                .stream()
                .filter(property -> property.getName().equals("textures"))
                .map(ProfileProperty::getValue)
                .findFirst()
                .orElse(null);
    }

    private static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
