package com.aethercane.aethercanelib.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Material;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MenuConfig<T extends ItemConfig> {

    private String title = "";
    private int rows = 54;

    @JsonProperty("filler-material")
    private Material fillerMaterial = Material.AIR;

    @JsonProperty("filler-slots")
    private List<Integer> fillerSlots = Collections.emptyList();

    private Map<String, T> items = Map.of();

    public String getTitle() {
        return title;
    }

    public int getRows() {
        return rows;
    }

    public Material getFillerMaterial() {
        return fillerMaterial;
    }

    public List<Integer> getFillerSlots() {
        return fillerSlots;
    }

    public Map<String, T> getItems() {
        return items;
    }
}
