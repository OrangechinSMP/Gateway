package hell0hd.orangechin.item;

import hell0hd.orangechin.Orangechin;
import hell0hd.orangechin.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CARMAPO_SPAWN_EGG = registerItem("carmapo_spawn_egg",
            new SpawnEggItem(ModEntities.CARMAPO, 0xFFFFFF, 0xFFFFFF, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Orangechin.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Orangechin.LOGGER.info("what if i added some freaking items as well wouldn't that be freaking awesome");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(CARMAPO_SPAWN_EGG);
        });
    }
}
