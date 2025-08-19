package com.aethercane.aethercanelib.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtil {

    public String fromLocation(Location location) {
        return location.getWorld().getName() + "," +
                location.getX() + "," +
                location.getY() + "," +
                location.getZ() + "," +
                location.getPitch() + "," +
                location.getYaw();
    }

    public Location toLocation(String string) {
        String[] arguments = string.split(",");
        World world = Bukkit.getWorld(arguments[0]);
        return new Location(world, Double.parseDouble(arguments[0]), Double.parseDouble(arguments[1]),
                Double.parseDouble(arguments[2]), Float.parseFloat(arguments[3]), Float.parseFloat(arguments[4]));
    }
}
