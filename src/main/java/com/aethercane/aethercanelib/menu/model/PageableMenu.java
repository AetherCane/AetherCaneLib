package com.aethercane.aethercanelib.menu.model;

import com.aethercane.aethercanelib.config.model.ItemConfig;
import com.aethercane.aethercanelib.config.model.PageableMenuConfig;
import com.aethercane.aethercanelib.util.ItemUtil;
import com.aethercane.aethercanelib.util.StringsUtil;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public abstract class PageableMenu<T> {

    protected final PageableMenuConfig<?> menuConfig;
    protected final TagResolver[] resolvers;
    protected final Player player;

    protected final PaginatedGui gui;

    public PageableMenu(Player player, PageableMenuConfig<?> menuConfig, TagResolver... resolvers) {
        this.menuConfig = menuConfig;
        this.resolvers = resolvers;
        this.player = player;

        this.gui = Gui.paginated()
                .title(StringsUtil.applyString(menuConfig.getTitle(), null, resolvers))
                .rows(menuConfig.getRows())
                .disableAllInteractions()
                .pageSize(menuConfig.getPageSlotSize()).create();
    }

    public void open() {
        addContent();
        gui.open(player);
    }

    protected void addContent() {
        List<Integer> fillerSlots = menuConfig.getFillerSlots();

        for (Integer slot : fillerSlots)
            gui.setItem(slot, ItemBuilder.from(menuConfig.getFillerMaterial()).name(Component.space()).asGuiItem());

        ItemConfig nextItemConfig = menuConfig.getItems().get("next");
        if(nextItemConfig != null) {
            GuiItem next = ItemUtil.createItemBuilder(nextItemConfig, player, resolvers)
                    .asGuiItem(event -> gui.next());
            gui.setItem(nextItemConfig.getSlots(), next);
        }

        ItemConfig previousItemConfig = menuConfig.getItems().get("previous");
        if(previousItemConfig != null) {
            GuiItem previous = ItemUtil.createItemBuilder(menuConfig.getItems().get("previous"), player, resolvers)
                    .asGuiItem(event -> gui.previous());
            gui.setItem(menuConfig.getItems().get("previous").getSlots(), previous);
        }

        getPageableObjects().forEach(pageableObject -> gui.addItem(getPageableItem(pageableObject)));
    }

    protected abstract Collection<T> getPageableObjects();

    protected abstract GuiItem getPageableItem(T pageableObject);

}
