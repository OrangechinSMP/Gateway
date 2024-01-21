package hell0hd.gateway.item;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.item.custom.BlindEyeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.datafixer.fix.BlockNameFix;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BLIND_EYE = registerItem("blind_eye", new BlindEyeItem(new FabricItemSettings()));
    public static final Item SCULK_POWDER = registerItem("sculk_powder", new Item(new FabricItemSettings()));

    private static void addItemsToFunctionalItemGroup(FabricItemGroupEntries entries) {entries.add(BLIND_EYE);}
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(BLIND_EYE); entries.add(SCULK_POWDER);
    }
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(BLIND_EYE);
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
