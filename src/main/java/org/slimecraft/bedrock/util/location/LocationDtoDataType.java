package org.slimecraft.bedrock.util.location;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NullMarked;

import java.util.UUID;

@NullMarked
public class LocationDtoDataType implements PersistentDataType<String, LocationDto> {
    public static final LocationDtoDataType INSTANCE = new LocationDtoDataType();

    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @Override
    public Class<LocationDto> getComplexType() {
        return LocationDto.class;
    }

    @Override
    public String toPrimitive(LocationDto complex, PersistentDataAdapterContext context) {

        return
                complex.getWorld() + "," +
                complex.getX() + "," +
                complex.getY() + "," +
                complex.getZ() + "," +
                complex.getBlockX() + "," +
                complex.getBlockY() + "," +
                complex.getBlockZ() + "," +
                complex.getPitch() + "," +
                complex.getYaw();
    }

    @Override
    public LocationDto fromPrimitive(String primitive, PersistentDataAdapterContext context) {
        final String[] parts = primitive.split(",");
        final UUID world = UUID.fromString(parts[0]);
        final double x = Double.parseDouble(parts[1]);
        final double y = Double.parseDouble(parts[2]);
        final double z = Double.parseDouble(parts[3]);
        final int blockX = Integer.parseInt(parts[4]);
        final int blockY = Integer.parseInt(parts[5]);
        final int blockZ = Integer.parseInt(parts[6]);
        final float pitch = Float.parseFloat(parts[7]);
        final float yaw = Float.parseFloat(parts[8]);

        return new LocationDto(world, blockX, blockY, blockZ, x, y, z, pitch, yaw);
    }
}
