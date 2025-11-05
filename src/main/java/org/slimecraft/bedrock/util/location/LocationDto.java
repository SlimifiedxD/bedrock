package org.slimecraft.bedrock.util.location;

import org.bukkit.Location;

import java.util.UUID;

public class LocationDto {
    private final UUID world;
    private final int blockX;
    private final int blockY;
    private final int blockZ;
    private final double x;
    private final double y;
    private final double z;
    private final float pitch;
    private final float yaw;

    public LocationDto(UUID world, int blockX, int blockY, int blockZ, double x, double y, double z, float pitch, float yaw) {
        this.world = world;
        this.blockX = blockX;
        this.blockY = blockY;
        this.blockZ = blockZ;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public LocationDto(Location location) {
        this(location.getWorld().getUID(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
    }

    public UUID getWorld() {
        return world;
    }

    public int getBlockX() {
        return blockX;
    }

    public int getBlockY() {
        return blockY;
    }

    public int getBlockZ() {
        return blockZ;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }
}
