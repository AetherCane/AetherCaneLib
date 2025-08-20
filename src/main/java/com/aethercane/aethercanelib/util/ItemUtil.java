package com.aethercane.aethercanelib.util;

import com.aethercane.aethercanelib.AetherCaneLib;
import com.aethercane.aethercanelib.config.model.ItemConfig;
import com.aethercane.aethercanelib.skin.service.SkinService;
import dev.triumphteam.gui.builder.item.BaseItemBuilder;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class ItemUtil {

    private static final SkinService skinService = AetherCaneLib.getInstance().getSkinService();

    public static BaseItemBuilder<? extends BaseItemBuilder<?>> createItemBuilder(ItemConfig itemConfig, @Nullable Player player, TagResolver... resolvers) {
        ItemBuilder itemBuilder = ItemBuilder.from(itemConfig.getMaterial())
                .name(StringsUtil.applyString(itemConfig.getDisplayName(), player, resolvers))
                .lore(StringsUtil.applyStringCollection(itemConfig.getLore(), player, resolvers))
                .glow(itemConfig.isGlowing());

        if (!itemConfig.getMaterial().equals(Material.PLAYER_HEAD)) {
            return itemBuilder;
        }

        String headData = itemConfig.getHeadData();
        if(headData.startsWith("head-"))
            return ItemBuilder.skull(itemBuilder.build()).texture(skinService.getTexture(headData.substring(5)));
        else if (headData.startsWith("texture-"))
            return ItemBuilder.skull(itemBuilder.build()).texture(headData.substring(8));

        return itemBuilder;
    }
}
