package com.aethercane.aethercanelib.menu.model;

import com.aethercane.aethercanelib.config.model.MenuConfig;
import com.aethercane.aethercanelib.util.StringsUtil;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;

import java.util.List;

public class Menu {

    protected final MenuConfig<?> menuConfig;
    protected final TagResolver[] resolvers;
    protected final Player player;

    protected final Gui gui;

    public Menu(Player player, MenuConfig<?> menuConfig, TagResolver... resolvers) {
        this.menuConfig = menuConfig;
        this.resolvers = resolvers;
        this.player = player;

        this.gui = Gui.gui()
                .title(StringsUtil.applyString(menuConfig.getTitle(), null, resolvers))
                .rows(menuConfig.getRows())
                .disableAllInteractions()
                .create();
    }

    public void open() {
        addContent();
        gui.open(player);
    }

    protected void addContent() {
        List<Integer> fillerSlots = menuConfig.getFillerSlots();

        if(menuConfig.isAutoFiller()) {
            gui.getFiller().fill(ItemBuilder.from(menuConfig.getFillerMaterial()).name(Component.space()).asGuiItem());
            return;
        }

        for (Integer slot : fillerSlots)
            gui.setItem(slot, ItemBuilder.from(menuConfig.getFillerMaterial()).name(Component.space()).asGuiItem());
    }
}
