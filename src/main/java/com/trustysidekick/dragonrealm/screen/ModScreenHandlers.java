package com.trustysidekick.dragonrealm.screen;

import com.trustysidekick.dragonrealm.DragonRealm;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<DragonForgeScreenHandler> DRAGON_FORGE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(DragonRealm.MOD_ID, "gem_polishing"),
                    new ExtendedScreenHandlerType<>(DragonForgeScreenHandler::new));

    public static void registerScreenHandlers() {
        DragonRealm.LOGGER.info("Registering Screen Handlers for " + DragonRealm.MOD_ID);
    }
}
