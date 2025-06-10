package hell0hd.gateway.block;

import hell0hd.gateway.Gateway;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {


    public static final Block CARMAPELLET_BLOCK = registerBlock("carmapellet_block", new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(1.0f).sounds(BlockSoundGroup.WART_BLOCK)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Gateway.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Gateway.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    private static Block registerBackportBlock(String name, Block block) {
        registerBackportBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of("minecraft", name), block);
    }

    private static void registerBackportBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of("minecraft", name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Gateway.LOGGER.info("oak so pale");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(CARMAPELLET_BLOCK);
        });
    }


}
