package hell0hd.gateway;

import hell0hd.gateway.block.ModBlocks;
import hell0hd.gateway.item.ModItems;
import hell0hd.gateway.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gateway implements ModInitializer {

    public static final String MOD_ID = "gateway";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);




    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();
        ModBlocks.registerItemGroups();
        ModSounds.initializeSounds();
        ModItems.registerModItems();
    }
}