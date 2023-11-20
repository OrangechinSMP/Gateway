package hell0hd.gateway.block;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.block.custom.ReinforcedDeepslateFrameBlock;
import hell0hd.gateway.block.custom.TheGatewayBlockX;
import hell0hd.gateway.block.custom.TheGatewayBlockZ;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.minecraft.block.Blocks.END_PORTAL_FRAME;

public class ModBlocks {
    public static final Block THE_GATEWAYX = registerBlock("the_gatewayx",
            new TheGatewayBlockX(FabricBlockSettings.copyOf(Blocks.NETHER_PORTAL).noCollision().resistance(-1.0f).ticksRandomly().strength(50.0f, 1200.0f).sounds(BlockSoundGroup.GLASS).luminance(state -> 11).nonOpaque()));

    public static final Block THE_GATEWAYZ = registerBlock("the_gatewayz",
            new TheGatewayBlockZ(FabricBlockSettings.copyOf(Blocks.NETHER_PORTAL).noCollision().resistance(-1.0f).ticksRandomly().strength(50.0f, 1200.0f).sounds(BlockSoundGroup.GLASS).luminance(state -> 11).nonOpaque()));

    public static final Block REINFORCED_STONE = registerBlock("reinforced_stone",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STONE).resistance(-1.0f).strength(55.0f, 1200.0f)));

    public static final Block STONE_PILLAR = registerBlock("stone_pillar",
    new PillarBlock(FabricBlockSettings.copyOf(Blocks.STONE).requiresTool().strength(1.5f, 6.0f)));

    public static final Block POLISHED_STONE = registerBlock("polished_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE).requiresTool().strength(1.5f, 6.0f)));

    public static final Block POLISHED_STONE_STAIRS = registerBlock("polished_stone_stairs",
            new StairsBlock(POLISHED_STONE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_STONE).strength(2.0f, 6.0f)));

    public static final Block POLISHED_STONE_WALL = registerBlock("polished_stone_wall",
            new WallBlock(AbstractBlock.Settings.copy(POLISHED_STONE).strength(2.0f, 6.0f)));

    public static final Block POLISHED_STONE_SLAB = registerBlock("polished_stone_slab",
            new SlabBlock(AbstractBlock.Settings.copy(POLISHED_STONE).strength(2.0f, 6.0f)));

    public static final Block REINFORCED_DEEPSLATE_FRAME = registerBlock("reinforced_deepslate_frame",
            new ReinforcedDeepslateFrameBlock(AbstractBlock.Settings.copy(END_PORTAL_FRAME).strength(-1.0F, 3600000.0F)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);

        return Registry.register(Registries.BLOCK, new Identifier(Gateway.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(Gateway.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        return item;
    }
    public static void registerItemGroups() {
        // Example of adding to existing Item Group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.STONE_PILLAR);
            entries.add(ModBlocks.POLISHED_STONE);
            entries.add(ModBlocks.POLISHED_STONE_SLAB);
            entries.add(ModBlocks.POLISHED_STONE_STAIRS);
            entries.add(ModBlocks.POLISHED_STONE_WALL);
            entries.add(ModBlocks.POLISHED_STONE);
            entries.add(ModBlocks.REINFORCED_STONE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.add(ModBlocks.THE_GATEWAYX);
            entries.add(ModBlocks.THE_GATEWAYZ);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModBlocks.REINFORCED_DEEPSLATE_FRAME);
        });
    }

    public static void registerModBlocks() {
        Gateway.LOGGER.debug("Registering ModBlocks for "+ Gateway.MOD_ID);
    }
}