package hell0hd.gateway.item;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.item.custom.BlindEyeItem;
import hell0hd.gateway.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item BLIND_EYE = registerItem("blind_eye", new BlindEyeItem(new FabricItemSettings()));
    public static final Item SCULK_POWDER = registerItem("sculk_powder", new Item(new FabricItemSettings()));
    public static final Item MUSIC_DISC_DROOPY = registerItem("music_disc_droopy", new MusicDiscItem(12, ModSounds.MUSIC_DISC_DROOPY, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 116));
    public static final Item MUSIC_DISC_EIGHTH = registerItem("music_disc_eighth", new MusicDiscItem(8,ModSounds.MUSIC_DISC_EIGHTH, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 283));
    public static final Item MUSIC_DISC_DOG = registerItem("music_disc_dog", new MusicDiscItem(14,ModSounds.MUSIC_DISC_DOG, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 147));
    public static final Item MUSIC_DISC_ELEVEN = registerItem("music_disc_eleven", new MusicDiscItem(11,ModSounds.MUSIC_DISC_ELEVEN, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 72));

    public static final Item COMMUNICATOR = registerItem("communicator", new Item(new FabricItemSettings()));
    private static void addItemsToFunctionalItemGroup(FabricItemGroupEntries entries) {entries.add(BLIND_EYE);}
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(BLIND_EYE); entries.add(SCULK_POWDER);
    }
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(BLIND_EYE); entries.add(MUSIC_DISC_DROOPY); entries.add(MUSIC_DISC_EIGHTH); entries.add(MUSIC_DISC_DOG); entries.add(COMMUNICATOR); entries.add(MUSIC_DISC_ELEVEN);
    }






    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Gateway.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Gateway.LOGGER.info("Registering Mod Items for " + Gateway.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModItems::addItemsToFunctionalItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);

    }
}
