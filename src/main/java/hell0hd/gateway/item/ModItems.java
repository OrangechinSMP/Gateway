package hell0hd.gateway.item;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.entity.ModEntities;
import hell0hd.gateway.item.custom.SuspiciousMassItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CARMAPO_SPAWN_EGG = registerItem("carmapo_spawn_egg",
            new SpawnEggItem(ModEntities.CARMAPO, 0xFFFFFF, 0xFFFFFF, new Item.Settings()));

    public static final Item SUSPICIOUS_MASS = registerItem("suspicious_mass",
            new SuspiciousMassItem(new Item.Settings().maxCount(16)));

    public static final Item PAUX = registerItem("paux", new Item(new Item.Settings().food(FoodComponents.CHICKEN)));
    public static final Item COOKED_PAUX = registerItem("cooked_paux", new Item(new Item.Settings().food(FoodComponents.COOKED_CHICKEN)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Gateway.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Gateway.LOGGER.info("what if i added some freaking items as well wouldn't that be freaking awesome");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(CARMAPO_SPAWN_EGG);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(SUSPICIOUS_MASS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(PAUX);
            entries.add(COOKED_PAUX);
        });
    }
}
