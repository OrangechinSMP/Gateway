package hell0hd.gateway.block;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.block.custom.ReinforcedDeepslateFrameBlock;

import hell0hd.gateway.block.custom.gateway.TheGatewayBlock;
import hell0hd.gateway.block.custom.gateway.TheGatewayBlockEntity;
import hell0hd.gateway.sound.ModBlockSoundGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.minecraft.block.Blocks.*;

public class ModBlocks {
    public static final Block THE_GATEWAY = registerBlock("the_gateway",
            new TheGatewayBlock(FabricBlockSettings.copyOf(Blocks.NETHER_PORTAL).noCollision().resistance(-1.0f).ticksRandomly().strength(50.0f, 1200.0f).sounds(BlockSoundGroup.GLASS).luminance(state -> 11).nonOpaque()));

    public static final BlockEntityType<TheGatewayBlockEntity> THE_GATEWAY_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(Gateway.MOD_ID, "the_gateway"),
            FabricBlockEntityTypeBuilder.create(TheGatewayBlockEntity::new, THE_GATEWAY).build()
    );

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

    public static final Block POLISHED_TUFF = registerMinecraftBlock(new Identifier("polished_tuff"),
    new Block(AbstractBlock.Settings.copy(TUFF).sounds(ModBlockSoundGroup.POLISHED_TUFF).requiresTool().strength(1.5F, 6.0F)));

    public static final Block POLISHED_TUFF_SLAB = registerMinecraftBlock(new Identifier("polished_tuff_slab"),
            new SlabBlock(AbstractBlock.Settings.copy(POLISHED_TUFF).requiresTool().strength(1.5F, 6.0F)));

    public static final Block POLISHED_TUFF_STAIRS = registerMinecraftBlock(new Identifier("polished_tuff_stairs"),
            new StairsBlock(POLISHED_TUFF.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_TUFF).requiresTool().strength(1.5F, 6.0F)));

    public static final Block POLISHED_TUFF_WALL = registerMinecraftBlock(new Identifier("polished_tuff_wall"),
            new WallBlock(AbstractBlock.Settings.copy(POLISHED_TUFF).requiresTool().strength(1.5F, 6.0F)));
    public static final Block TUFF_SLAB = registerMinecraftBlock(new Identifier("tuff_slab"), new SlabBlock(AbstractBlock.Settings.copy(TUFF).requiresTool().strength(1.5F, 6.0F)));
    public static final Block TUFF_STAIRS = registerMinecraftBlock(new Identifier("tuff_stairs"), new StairsBlock(TUFF.getDefaultState(), AbstractBlock.Settings.copy(TUFF).requiresTool().strength(1.5F, 6.0F)));
    public static final Block TUFF_WALL = registerMinecraftBlock(new Identifier("tuff_wall"), new WallBlock(AbstractBlock.Settings.copy(TUFF).requiresTool().strength(1.5F, 6.0F)));
    public static final Block CHISELED_TUFF = registerMinecraftBlock(new Identifier("chiseled_tuff"), new Block(AbstractBlock.Settings.copy(TUFF)));
    public static final Block CHISELED_COPPER = registerMinecraftBlock(new Identifier("chiseled_copper"), new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(COPPER_BLOCK)));

    public static final Block EXPOSED_CHISELED_COPPER = registerMinecraftBlock(new Identifier("exposed_chiseled_copper"), new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(COPPER_BLOCK)));

    public static final Block WEATHERED_CHISELED_COPPER = registerMinecraftBlock(new Identifier("weathered_chiseled_copper"), new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(COPPER_BLOCK)));

    public static final Block OXIDIZED_CHISELED_COPPER = registerMinecraftBlock(new Identifier("oxidized_chiseled_copper"), new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(COPPER_BLOCK)));

    public static final Block WAXED_CHISELED_COPPER = registerMinecraftBlock(new Identifier("waxed_chiseled_copper"), new Block(AbstractBlock.Settings.copy(CHISELED_COPPER)));

    public static final Block WAXED_EXPOSED_CHISELED_COPPER = registerMinecraftBlock(new Identifier("waxed_exposed_chiseled_copper"), new Block(AbstractBlock.Settings.copy(EXPOSED_CHISELED_COPPER)));

    public static final Block WAXED_WEATHERED_CHISELED_COPPER = registerMinecraftBlock(new Identifier("waxed_weathered_chiseled_copper"), new Block(AbstractBlock.Settings.copy(WEATHERED_CHISELED_COPPER)));

    public static final Block WAXED_OXIDIZED_CHISELED_COPPER = registerMinecraftBlock(new Identifier("waxed_oxidized_chiseled_copper"), new Block(AbstractBlock.Settings.copy(OXIDIZED_CHISELED_COPPER)));

    public static final Block TUFF_BRICKS = registerMinecraftBlock(new Identifier("tuff_bricks"), new Block(AbstractBlock.Settings.copy(TUFF).sounds(ModBlockSoundGroup.TUFF_BRICKS)));

    public static final Block TUFF_BRICK_SLAB = registerMinecraftBlock(new Identifier("tuff_brick_slab"), new SlabBlock(AbstractBlock.Settings.copy(TUFF).requiresTool().strength(1.5F, 6.0F)));
    public static final Block TUFF_BRICK_STAIRS = registerMinecraftBlock(new Identifier("tuff_brick_stairs"), new StairsBlock(TUFF.getDefaultState(), AbstractBlock.Settings.copy(TUFF).requiresTool().strength(1.5F, 6.0F)));
    public static final Block TUFF_BRICK_WALL = registerMinecraftBlock(new Identifier("tuff_brick_wall"), new WallBlock(AbstractBlock.Settings.copy(TUFF).requiresTool().strength(1.5F, 6.0F)));
    public static final Block CHISELED_TUFF_BRICKS = registerMinecraftBlock(new Identifier("chiseled_tuff_bricks"), new Block(AbstractBlock.Settings.copy(TUFF)));





    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);

        return Registry.register(Registries.BLOCK, new Identifier(Gateway.MOD_ID, name), block);
    }

    private static Block registerMinecraftBlock(Identifier id, Block block) {
        registerMinecraftBlockItem(id, block);

        return Registry.register(Registries.BLOCK, id, block);
    }

    private static void registerMinecraftBlockItem(Identifier id, Block block) {
        Registry.register(Registries.ITEM, id, new BlockItem(block, new FabricItemSettings()));
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Gateway.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
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
            entries.add(ModBlocks.POLISHED_TUFF);
            entries.add(ModBlocks.POLISHED_TUFF_SLAB);
            entries.add(ModBlocks.POLISHED_TUFF_STAIRS);
            entries.add(ModBlocks.POLISHED_TUFF_WALL);
            entries.add(ModBlocks.TUFF_SLAB);
            entries.add(ModBlocks.TUFF_STAIRS);
            entries.add(ModBlocks.TUFF_WALL);
            entries.add(ModBlocks.CHISELED_COPPER);
            entries.add(ModBlocks.EXPOSED_CHISELED_COPPER);
            entries.add(ModBlocks.WEATHERED_CHISELED_COPPER);
            entries.add(ModBlocks.OXIDIZED_CHISELED_COPPER);
            entries.add(ModBlocks.WAXED_CHISELED_COPPER);
            entries.add(ModBlocks.WAXED_EXPOSED_CHISELED_COPPER);
            entries.add(ModBlocks.WAXED_WEATHERED_CHISELED_COPPER);
            entries.add(ModBlocks.WAXED_OXIDIZED_CHISELED_COPPER);
            entries.add(ModBlocks.CHISELED_TUFF);
            entries.add(ModBlocks.TUFF_BRICKS);
            entries.add(ModBlocks.TUFF_BRICK_SLAB);
            entries.add(ModBlocks.TUFF_BRICK_STAIRS);
            entries.add(ModBlocks.TUFF_BRICK_WALL);
            entries.add(ModBlocks.CHISELED_TUFF_BRICKS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.add(ModBlocks.THE_GATEWAY);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModBlocks.REINFORCED_DEEPSLATE_FRAME);
        });
    }

    public static void registerModBlocks() {
        Gateway.LOGGER.debug("Registering ModBlocks for "+ Gateway.MOD_ID);
    }
}