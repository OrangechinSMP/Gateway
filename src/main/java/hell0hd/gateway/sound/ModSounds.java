package hell0hd.gateway.sound;

import hell0hd.gateway.Gateway;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


public class ModSounds {
    public static SoundEvent GATEWAY_AMBIENCE = registerSoundEvent("gateway_ambience");
    public static SoundEvent DEEP_DARK_PORTAL_FRAME_FILL = registerSoundEvent("block.deep_dark_portal_frame.fill");
    public static SoundEvent DEEP_DARK_PORTAL_SPAWN = registerSoundEvent("block.deep_dark_portal.spawn");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Gateway.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void initializeSounds() {
        Gateway.LOGGER.info("Registering " + Gateway.MOD_ID + " Sounds");
    }
}
