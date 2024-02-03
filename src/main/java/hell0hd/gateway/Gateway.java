package hell0hd.gateway;

import hell0hd.gateway.block.ModBlocks;
import hell0hd.gateway.entity.ModEntities;
import hell0hd.gateway.item.ModItems;
import hell0hd.gateway.potion.ModPotions;
import hell0hd.gateway.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gateway implements ModInitializer {

    public static final String MOD_ID = "gateway";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static MinecraftServer INSTANCE = null;


    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();
        ModBlocks.registerItemGroups();
        ModSounds.initializeSounds();
        ModItems.registerModItems();
        ModPotions.registerPotions();
        ModEntities.registerModEntities();

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            INSTANCE = server;
        });
    }
}