package bikeboys.litematicabiggerrenderdistance.client.mixin;

import bikeboys.litematicabiggerrenderdistance.client.ModConfigs;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import fi.dy.masa.litematica.schematic.placement.SchematicPlacementManager;
import net.minecraft.client.Options;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SchematicPlacementManager.class)
public class SchematicPlacementManagerMixin {

    @Inject(method = "canHandleChunk", at = @At("HEAD"), cancellable = true)
    private void lbrd$canHandle(ClientLevel level, int cx, int cz, CallbackInfoReturnable<Boolean> cir) {
        if (ModConfigs.inRange(cx, cz)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "onClientChunkUnload", at = @At("HEAD"), cancellable = true)
    private void lbrd$keepChunk(int cx, int cz, CallbackInfo ci) {
        if (ModConfigs.inRange(cx, cz)) {
            ci.cancel();
        }
    }

    @WrapOperation(
            method = "onClientTick",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/Options;getEffectiveRenderDistance()I"),
            require = 1)
    private int lbrd$fixerRadius(Options options, Operation<Integer> original) {
        return Math.max(original.call(options), ModConfigs.distance());
    }
}