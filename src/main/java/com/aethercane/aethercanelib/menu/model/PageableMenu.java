package com.aethercane.aethercanelib.menu.model;

import com.aethercane.aethercanelib.config.model.PageableMenuConfig;
import com.aethercane.aethercanelib.util.StringsUtil;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public abstract class PageableMenu<T> {

    private final PageableMenuConfig menuConfig;

    protected final PaginatedGui gui;

    public PageableMenu(PageableMenuConfig menuConfig, TagResolver... resolvers) {
        this.menuConfig = menuConfig;
        this.gui = Gui.paginated()
                .title(StringsUtil.applyString(menuConfig.getTitle(), null, resolvers))
                .rows(menuConfig.getRows())
                .disableAllInteractions()
                .pageSize(menuConfig.getPageSlotSize()).create();
    }

    public void open(Player player) {
        addContent();
        gui.open(player);
    }

    protected void addContent() {
        List<Integer> fillerSlots = menuConfig.getFillerSlots();

        for (Integer slot : fillerSlots)
            gui.setItem(slot, ItemBuilder.from(menuConfig.getFillerMaterial()).name(Component.space()).asGuiItem());

        getPageableObjects().forEach(pageableObject -> gui.addItem(getPageableItem(pageableObject)));
    }

    protected abstract Collection<T> getPageableObjects();

    protected abstract GuiItem getPageableItem(T pageableObject);

}
