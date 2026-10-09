package fr.camille.swapclicks;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class SwapClicks implements ClientModInitializer {

    public static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath("swapclicks", "main"));

    // Touche par défaut : R (modifiable dans Options > Contrôles > Touches)
    public static final KeyMapping SWAP_KEY = new KeyMapping(
            "key.swapclicks.swap", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, CATEGORY);

    @Override
    public void onInitializeClient() {
        // Rien à faire : le keybind est ajouté par OptionsMixin, le tick est géré par MinecraftMixin.
    }

    /** Échange les touches assignées à "Attaquer/Détruire" et "Utiliser/Poser". */
    public static void swap(Minecraft mc) {
        Options o = mc.options;
        InputConstants.Key attack = InputConstants.getKey(o.keyAttack.saveString());
        InputConstants.Key use = InputConstants.getKey(o.keyUse.saveString());

        o.keyAttack.setKey(use);
        o.keyUse.setKey(attack);
        KeyMapping.resetMapping();
        o.save();

        if (mc.gui != null) {
            mc.gui.setOverlayMessage(Component.literal("Clics inversés"), false);
        }
    }
}
