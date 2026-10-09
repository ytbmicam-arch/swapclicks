package fr.camille.swapclicks.mixin;

import fr.camille.swapclicks.SwapClicks;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void swapclicks$tick(CallbackInfo ci) {
        Minecraft mc = (Minecraft) (Object) this;
        while (SwapClicks.SWAP_KEY.consumeClick()) {
            if (mc.player != null && mc.screen == null) {
                SwapClicks.swap(mc);
            }
        }
    }
}
