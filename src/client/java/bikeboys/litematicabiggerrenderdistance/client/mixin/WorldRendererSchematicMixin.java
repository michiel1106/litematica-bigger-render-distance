package bikeboys.litematicabiggerrenderdistance.client.mixin;

import bikeboys.litematicabiggerrenderdistance.client.ModConfigs;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import fi.dy.masa.litematica.render.schematic.WorldRendererSchematic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldRendererSchematic.class)
public class WorldRendererSchematicMixin {

    @Shadow @Final
    private Minecraft mc;

    @WrapOperation(
            method = {"loadRenderers", "setupTerrain"},
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;"),
            require = 3)
    private Object lbrd$distance(OptionInstance<?> instance, Operation<Object> original) {
        if (instance == this.mc.options.renderDistance()) {
            return ModConfigs.distance();
        }
        return original.call(instance);
    }
}