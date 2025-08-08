package com.aethercane.aethercanelib.util;

import com.aethercane.aethercanelib.AetherCaneLib;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.logging.Level;

public class StringsUtil {

    private final static LegacyComponentSerializer ampersandSerializer = LegacyComponentSerializer.legacyAmpersand();
    private final static LegacyComponentSerializer sectionSerializer = LegacyComponentSerializer.legacySection();

    public static Component applyPlaceholder(String input, Player player) {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            AetherCaneLib.getPlugin().getServer().getLogger().log(Level.WARNING, "PlaceholderAPI not found, returned original string.");
            return Component.text(input);
        }
        return Component.text(PlaceholderAPI.setPlaceholders(player, input));
    }

    private static Component applyPlaceholder(Component component, Player player) {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            AetherCaneLib.getPlugin().getServer().getLogger().log(Level.WARNING, "PlaceholderAPI not found, returned original string.");
            return component;
        }
        String input = sectionSerializer.serialize(component);
        return Component.text(PlaceholderAPI.setPlaceholders(player, input));
    }

    public static Component applyColor(String input, TagResolver... resolvers) {
        MiniMessage mm = MiniMessage.miniMessage();
        Component legacy = ampersandSerializer.deserialize(input);
        String minimessage = mm.serialize(legacy).replace("\\", "");
        return mm.deserialize(minimessage, resolvers);
    }

    private static Component applyColor(Component component, TagResolver... resolvers) {
        MiniMessage mm = MiniMessage.miniMessage();
        String input = sectionSerializer.serialize(component);
        Component legacy = ampersandSerializer.deserialize(input);
        String minimessage = mm.serialize(legacy).replace("\\", "");
        return mm.deserialize(minimessage, resolvers);
    }

    public static Component applyString(String input, @Nullable Player player, TagResolver... resolvers) {
        Component component = Component.text(input);
        if (player != null) {
            component = applyPlaceholder(component, player);
        }
        component = applyColor(component, resolvers);
        return component;
    }
}
