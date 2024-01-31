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
    public static SoundEvent RACCKIN_SCURRY = registerSoundEvent("origins.racckin.scurry");
    public static SoundEvent NATURE_GOD_VINE_SHOOT = registerSoundEvent("origins.nature_god.vine_whack.vine_shoot");
    public static SoundEvent NATURE_GOD_VINE_HIT = registerSoundEvent("origins.nature_god.vine_whack.vine_hit");
    public static SoundEvent NATURE_GOD_PHOTOSYNTHESIS_HEAL = registerSoundEvent("origins.nature_god.photosynthesis.heal");
    public static SoundEvent GLITCH_RANDOM_TP = registerSoundEvent("origins.glitch.random_tp.teleport");
    public static SoundEvent BANDIT_STASH_OPEN = registerSoundEvent("origins.bandit.stash.open");
    public static SoundEvent BANDIT_STASH_CLOSE = registerSoundEvent("origins.bandit.stash.close");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Gateway.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void initializeSounds() {
        Gateway.LOGGER.info("Registering " + Gateway.MOD_ID + " Sounds");
    }
}
