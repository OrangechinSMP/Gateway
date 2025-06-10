package hell0hd.gateway.sounds;


import hell0hd.gateway.Gateway;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent ENTITY_CARMAPO_AMBIENT = registerSoundEvent("entity.carmapo.ambient");
    public static final SoundEvent ENTITY_CARMAPO_DEATH = registerSoundEvent("entity.carmapo.death");
    public static final SoundEvent ENTITY_CARMAPO_HURT = registerSoundEvent("entity.carmapo.hurt");
    public static final SoundEvent ENTITY_CARMAPO_GROWL = registerSoundEvent("entity.carmapo.growl");
    public static final SoundEvent ENTITY_CARMAPO_MASS = registerSoundEvent("entity.carmapo.mass");
    public static SoundEvent MUSIC_DISC_DROOPY = registerSoundEvent("music_disc.droopy");
    public static SoundEvent MUSIC_DISC_ELEVEN = registerSoundEvent("music_disc.eleven");
    public static SoundEvent MUSIC_DISC_TEARS = registerSoundEvent("music_disc.tears");
    public static final SoundEvent SPAWNER_BREAK = registerBackportSoundEvent("block.spawner.break");
    public static final SoundEvent SPAWNER_FALL = registerBackportSoundEvent("block.spawner.fall");
    public static final SoundEvent SPAWNER_HIT = registerBackportSoundEvent("block.spawner.hit");
    public static final SoundEvent SPAWNER_PLACE = registerBackportSoundEvent("block.spawner.place");
    public static final SoundEvent SPAWNER_STEP = registerBackportSoundEvent("block.spawner.step");

    public static final BlockSoundGroup SPAWNER = new BlockSoundGroup(
            1f,
            1f,
            SPAWNER_BREAK,
            SPAWNER_FALL,
            SPAWNER_STEP,
            SPAWNER_HIT,
            SPAWNER_PLACE);



    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Gateway.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    private static SoundEvent registerBackportSoundEvent(String name) {
        Identifier id = Identifier.of("minecraft", name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Gateway.LOGGER.info("carmapo sound :DDD");
    }
}
