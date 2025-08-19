package com.aethercane.aethercanelib.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class PageableMenuConfig extends MenuConfig {

    @JsonProperty("page-slot-size")
    private int pageSlotSize;

    public int getPageSlotSize() {
        return pageSlotSize;
    }
}
