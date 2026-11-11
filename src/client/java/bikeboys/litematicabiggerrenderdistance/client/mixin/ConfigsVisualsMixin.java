package bikeboys.litematicabiggerrenderdistance.client.mixin;

import bikeboys.litematicabiggerrenderdistance.client.ModConfigs;
import com.google.common.collect.ImmutableList;
import fi.dy.masa.litematica.config.Configs;
import fi.dy.masa.malilib.config.IConfigBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Configs.Visuals.class)
public abstract class ConfigsVisualsMixin {

    @Mutable @Final @Shadow
    public static ImmutableList<IConfigBase> OPTIONS;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void lbrd$addOption(CallbackInfo ci) {
        if (!OPTIONS.contains(ModConfigs.SCHEMATIC_RENDER_DISTANCE)) {
            OPTIONS = ImmutableList.<IConfigBase>builder()
                    .addAll(OPTIONS)
                    .add(ModConfigs.SCHEMATIC_RENDER_DISTANCE)
                    .build();
        }
    }
}