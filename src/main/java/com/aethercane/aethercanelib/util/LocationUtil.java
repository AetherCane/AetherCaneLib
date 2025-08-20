package com.aethercane.aethercanelib.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;

public class LocationUtil {

    private static final BlockFace[] RADIAL = {BlockFace.NORTH, BlockFace.NORTH_EAST,
            BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST,
            BlockFace.WEST, BlockFace.NORTH_WEST};

    public static String fromLocation(Location location) {
        return location.getWorld().getName() + "," +
                location.getX() + "," +
                location.getY() + "," +
                location.getZ() + "," +
                location.getPitch() + "," +
                location.getYaw();
    }

    public static Location toLocation(String string) {
        String[] arguments = string.split(",");

        World world = Bukkit.getWorld(arguments[0]);
        if(world == null) {
            throw new RuntimeException("not found " + arguments[0] + " named world");
        }

        return new Location(world,
                Double.parseDouble(arguments[1]),
                Double.parseDouble(arguments[2]),
                Double.parseDouble(arguments[3]),
                Float.parseFloat(arguments[4]),
                Float.parseFloat(arguments[5]));
    }

    public static Location alignDirection(Location loc) {
        Location location = loc.clone();
        location.setPitch(0);
        switch (yawToFace(location.getYaw())) {
            case NORTH -> location.setYaw(0);
            case NORTH_EAST -> location.setYaw(45);
            case EAST -> location.setYaw(90);
            case SOUTH_EAST -> location.setYaw(135);
            case SOUTH -> location.setYaw(180);
            case SOUTH_WEST -> location.setYaw(225);
            case WEST -> location.setYaw(270);
            case NORTH_WEST -> location.setYaw(360);
        }
        return location;
    }

    private static BlockFace yawToFace(float yaw) {
        return RADIAL[Math.round(yaw / 45) & 0x7];
    }
}
