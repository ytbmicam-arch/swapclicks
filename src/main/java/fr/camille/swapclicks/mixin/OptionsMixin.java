package fr.camille.swapclicks.mixin;

import fr.camille.swapclicks.SwapClicks;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public class OptionsMixin {
    @Mutable @Final @Shadow public KeyMapping[] keyMappings;

    // Ajoute notre keybind à la liste avant le chargement d'options.txt
    @Inject(method = "load", at = @At("HEAD"))
    private void swapclicks$addKey(CallbackInfo ci) {
        if (!ArrayUtils.contains(keyMappings, SwapClicks.SWAP_KEY)) {
            keyMappings = ArrayUtils.add(keyMappings, SwapClicks.SWAP_KEY);
        }
    }
}
