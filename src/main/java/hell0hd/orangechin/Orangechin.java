package hell0hd.orangechin;

import hell0hd.orangechin.entity.ModEntities;
import hell0hd.orangechin.entity.custom.CarmapoEntity;
import hell0hd.orangechin.item.ModItems;
import hell0hd.orangechin.sounds.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Orangechin implements ModInitializer {
	public static final String MOD_ID = "orangechin";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntities.registerModEntities();
		ModItems.registerModItems();
		ModSounds.registerSounds();

		LOGGER.info("my stupid chud son");

		FabricDefaultAttributeRegistry.register(ModEntities.CARMAPO, CarmapoEntity.createAttributes());
	}
}