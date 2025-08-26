package com.aethercane.aethercanelib.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.entity.Item;

import java.util.Collection;

public class PageableMenuConfig<T extends ItemConfig> extends MenuConfig<T> {

    @JsonProperty("page-slot-size")
    private int pageSlotSize;

    public int getPageSlotSize() {
        return pageSlotSize;
    }
}
