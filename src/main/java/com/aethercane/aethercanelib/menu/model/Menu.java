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

    private final MenuConfig menuConfig;

    protected final Gui gui;

    public Menu(MenuConfig menuConfig, TagResolver... resolvers) {
        this.menuConfig = menuConfig;
        this.gui = Gui.gui()
                .title(StringsUtil.applyString(menuConfig.getTitle(), null, resolvers))
                .rows(menuConfig.getRows())
                .disableAllInteractions()
                .create();
    }

    public void open(Player player) {
        addContent();
        gui.open(player);
    }

    protected void addContent() {
        List<Integer> fillerSlots = menuConfig.getFillerSlots();

        for (Integer slot : fillerSlots)
            gui.setItem(slot, ItemBuilder.from(menuConfig.getFillerMaterial()).name(Component.space()).asGuiItem());
    }
}
