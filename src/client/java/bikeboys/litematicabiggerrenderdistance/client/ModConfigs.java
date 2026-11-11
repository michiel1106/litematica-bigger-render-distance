package bikeboys.litematicabiggerrenderdistance.client;

import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.util.EntityUtils;
import net.minecraft.world.entity.Entity;

public class ModConfigs {

    public static final ConfigInteger SCHEMATIC_RENDER_DISTANCE =
            new ConfigInteger("schematicRenderDistance", 16, 2, 512, true,
                    "Render distance in chunks for schematics");

    public static int distance() {
        return SCHEMATIC_RENDER_DISTANCE.getIntegerValue();
    }

    public static boolean inRange(int cx, int cz) {
        Entity e = EntityUtils.getCameraEntity();
        if (e == null) return false;
        int d = distance();
        return Math.abs(cx - e.chunkPosition().x()) <= d
                && Math.abs(cz - e.chunkPosition().z()) <= d;
    }
}