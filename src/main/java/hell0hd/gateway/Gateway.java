package hell0hd.gateway;

import hell0hd.gateway.block.ModBlocks;
import hell0hd.gateway.entity.ModEntities;
import hell0hd.gateway.entity.custom.CarmapoEntity;
import hell0hd.gateway.item.ModItems;
import hell0hd.gateway.potion.ModPotions;
import hell0hd.gateway.sounds.ModSounds;
import hell0hd.gateway.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Gateway implements ModInitializer {
	public static final String MOD_ID = "gateway";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier idBackport(String string) {
		return Identifier.of("minecraft", string);
	}

	@Override
	public void onInitialize() {
		ModEntities.registerModEntities();
		ModItems.registerModItems();
		ModSounds.registerSounds();
		ModWorldGeneration.generateModWorldGen();
		ModPotions.registerPotions();
		ModBlocks.registerModBlocks();

		LOGGER.info("my stupid chud son");

		FabricDefaultAttributeRegistry.register(ModEntities.CARMAPO, CarmapoEntity.createAttributes());
		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.GOLDEN_APPLE, ModPotions.ABSORPTION);
			builder.registerPotionRecipe(ModPotions.ABSORPTION, Items.REDSTONE, ModPotions.LONG_ABSORPTION);
			builder.registerPotionRecipe(ModPotions.ABSORPTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_ABSORPTION);
			builder.registerPotionRecipe(Potions.WATER, ModItems.CARMAPELLETS, Potions.AWKWARD);
		});


	}
}