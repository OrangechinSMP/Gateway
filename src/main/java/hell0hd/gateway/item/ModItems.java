package hell0hd.gateway.item;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.block.ModBlocks;
import hell0hd.gateway.entity.ModEntities;
import hell0hd.gateway.item.custom.IOUItem;
import hell0hd.gateway.item.custom.SuspiciousMassItem;
import hell0hd.gateway.sounds.ModJukeboxSongs;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BannerPatternTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item CARMAPO_SPAWN_EGG = registerItem("carmapo_spawn_egg",
            new SpawnEggItem(ModEntities.CARMAPO, 0xFFFFFF, 0xFFFFFF, new Item.Settings()));

    public static final Item SUSPICIOUS_MASS = registerItem("suspicious_mass",
            new SuspiciousMassItem(new Item.Settings().maxCount(16)));

    public static final Item PAUX = registerItem("paux", new Item(new Item.Settings().food(ModFoodComponents.PAUX)));
    public static final Item COOKED_PAUX = registerItem("cooked_paux", new Item(new Item.Settings().food(FoodComponents.COOKED_CHICKEN)));

    public static final Item MUSIC_DISC_DROOPY = registerItem("music_disc_droopy", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ModJukeboxSongs.DROOPY)));

    public static final Item MUSIC_DISC_ELEVEN = registerItem("music_disc_eleven", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ModJukeboxSongs.ELEVEN)));

    public static final Item CARMAPELLETS = registerItem("carmapellets", new Item(new Item.Settings()));

    public static final Item BORDURE_INDENTED_BANNER_PATTERN = registerBackportItem("bordure_indented_banner_pattern", new BannerPatternItem(BackportBannerPatterns.BORDURE_INDENTED_PATTERN_ITEM, new Item.Settings().maxCount(1)));

    public static final Item FIELD_MASONED_BANNER_PATTERN = registerBackportItem("field_masoned_banner_pattern", new BannerPatternItem(BackportBannerPatterns.FIELD_MASONED_PATTERN_ITEM, new Item.Settings().maxCount(1)));

    public static final Item MUSIC_DISC_TEARS = registerBackportItem("music_disc_tears", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ModJukeboxSongs.TEARS)));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Gateway.MOD_ID, name), item);
    }

    private static Item registerBackportItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of("minecraft", name), item);
    }

    public static void registerModItems() {
        Gateway.LOGGER.info("what if i added some freaking items as well wouldn't that be freaking awesome");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(CARMAPO_SPAWN_EGG);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(SUSPICIOUS_MASS);
            entries.add(CARMAPELLETS);
            entries.add(FIELD_MASONED_BANNER_PATTERN);
            entries.add(BORDURE_INDENTED_BANNER_PATTERN);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(PAUX);
            entries.add(COOKED_PAUX);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(MUSIC_DISC_DROOPY);
            entries.add(MUSIC_DISC_ELEVEN);
            entries.add(MUSIC_DISC_TEARS);
            entries.add(Items.BUNDLE);
        });
    }
}
