package hell0hd.orangechin;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Orangechin implements ModInitializer {
	public static final String MOD_ID = "orangechin";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("my stupid chud son");
	}
}