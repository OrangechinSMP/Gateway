package hell0hd.orangechin.sounds;

import hell0hd.orangechin.Orangechin;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent ENTITY_CARMAPO_AMBIENT = registerSoundEvent("entity.carmapo.ambient");
    public static final SoundEvent ENTITY_CARMAPO_DEATH = registerSoundEvent("entity.carmapo.death");
    public static final SoundEvent ENTITY_CARMAPO_HURT = registerSoundEvent("entity.carmapo.hurt");
    public static final SoundEvent ENTITY_CARMAPO_GROWL = registerSoundEvent("entity.carmapo.growl");
    public static final SoundEvent ENTITY_CARMAPO_MASS = registerSoundEvent("entity.carmapo.mass");


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Orangechin.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Orangechin.LOGGER.info("carmapo sound :DDD");
    }
}
