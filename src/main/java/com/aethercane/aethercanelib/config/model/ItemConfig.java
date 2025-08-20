package com.aethercane.aethercanelib.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Material;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ItemConfig {

    @JsonProperty("display-name")
    private String displayName = "";

    private Material material = Material.AIR;

    @JsonProperty("model-data")
    private int modelData;

    private int amount = 1;

    private boolean glowing;

    @JsonProperty("head-data")
    private String headData;

    private List<String> lore = Collections.emptyList();
    private List<Integer> slots = Collections.emptyList();

    public String getDisplayName() {
        return displayName;
    }

    public Material getMaterial() {
        return material;
    }

    public int getModelData() {
        return modelData;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isGlowing() {
        return glowing;
    }

    public String getHeadData() {
        return headData;
    }

    public Collection<String> getLore() {
        return lore;
    }

    public Collection<Integer> getSlots() {
        return slots;
    }
}
