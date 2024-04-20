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
    public static SoundEvent GOOSE_HISS = registerSoundEvent("origins.goose.hiss.hiss");
    public static SoundEvent GOOSE_TAKE_OFF_LAUNCH = registerSoundEvent("origins.goose.take_off.launch");
    public static SoundEvent WATER_ELEMENTAL_WATERBOLT_SHOOT = registerSoundEvent("origins.water_elemental.waterbolt.shoot");
    public static SoundEvent WATER_ELEMENTAL_WATERBOLT_HIT = registerSoundEvent("origins.water_elemental.waterbolt.hit");
    public static SoundEvent WATER_ELEMENTAL_PROPEL_PROPEL = registerSoundEvent("origins.water_elemental.propel.propel");
    public static SoundEvent ENDBORN_FOCUSED_TELEPORT = registerSoundEvent("origins.endborn.focused_teleport");
    public static SoundEvent ENDBORN_ZEALOT = registerSoundEvent("origins.endborn.zealot");
    public static SoundEvent ENDBORN_RAGE = registerSoundEvent("origins.endborn.rage");
    public static SoundEvent ENDBORN_UNFOCUSED_TELEPORT = registerSoundEvent("origins.endborn.unfocused_teleport");
    public static SoundEvent DWARF_DRILL = registerSoundEvent("origins.dwarf.drill");
    public static SoundEvent UNDEAD_NECROMANCE = registerSoundEvent("origins.undead.necromance");
    public static SoundEvent ORIGIN_SKELETON_EAT_IT = registerSoundEvent("origins.undead.necromance.skeletons.eat_it");
    public static SoundEvent MUSIC_DISC_DROOPY = registerSoundEvent("music_disc.droopy");
    public static SoundEvent MUSIC_DISC_EIGHTH = registerSoundEvent("music_disc.eighth");
    public static SoundEvent MUSIC_DISC_DOG = registerSoundEvent("music_disc.dog");
    public static SoundEvent MUSIC_DISC_ELEVEN = registerSoundEvent("music_disc.eleven");
    public static SoundEvent BLOCK_POLISHED_TUFF_BREAK = registerSoundEvent("block.polished_tuff.break");
    public static SoundEvent BLOCK_POLISHED_TUFF_STEP = registerSoundEvent("block.polished_tuff.step");
    public static SoundEvent BLOCK_POLISHED_TUFF_PLACE = registerSoundEvent("block.polished_tuff.place");
    public static SoundEvent BLOCK_POLISHED_TUFF_HIT = registerSoundEvent("block.polished_tuff.hit");
    public static SoundEvent BLOCK_POLISHED_TUFF_FALL = registerSoundEvent("block.polished_tuff.fall");
    public static SoundEvent ENBORN_TELEPORT_FAIL = registerSoundEvent("origins.endborn.teleport_fail");
    public static SoundEvent ASSASSIN_DASH = registerSoundEvent("origins.assassin.dash");
    public static SoundEvent ASSASSIN_GRAPPLE = registerSoundEvent("origins.assassin.grapple");
    public static final SoundEvent BLOCK_COPPER_GRATE_BREAK = registerSoundEvent("block.copper_grate.break");
    public static final SoundEvent BLOCK_COPPER_GRATE_STEP = registerSoundEvent("block.copper_grate.step");
    public static final SoundEvent BLOCK_COPPER_GRATE_PLACE = registerSoundEvent("block.copper_grate.place");
    public static final SoundEvent BLOCK_COPPER_GRATE_HIT = registerSoundEvent("block.copper_grate.hit");
    public static final SoundEvent BLOCK_COPPER_GRATE_FALL = registerSoundEvent("block.copper_grate.fall");
    public static final SoundEvent BLOCK_TUFF_BRICKS_BREAK = registerSoundEvent("block.tuff_bricks.break");
    public static final SoundEvent BLOCK_TUFF_BRICKS_FALL = registerSoundEvent("block.tuff_bricks.fall");
    public static final SoundEvent BLOCK_TUFF_BRICKS_HIT = registerSoundEvent("block.tuff_bricks.hit");
    public static final SoundEvent BLOCK_TUFF_BRICKS_PLACE = registerSoundEvent("block.tuff_bricks.place");
    public static final SoundEvent BLOCK_TUFF_BRICKS_STEP = registerSoundEvent("block.tuff_bricks.step");
    public static SoundEvent ORIGINS_FELINE_LIVES_LIFE_LOST = registerSoundEvent("origins.feline.lives.life_lost");
    public static SoundEvent ORIGINS_FELINE_LIVES_LAST_LIFE = registerSoundEvent("origins.feline.lives.last_life");
    public static SoundEvent ORIGINS_FELINE_LEAP = registerSoundEvent("origins.feline.leap");
    public static SoundEvent ORIGINS_FELINE_REVIVE_REVIVE = registerSoundEvent("origins.feline.revive.revive");
    public static SoundEvent ORIGINS_FELINE_PHANTOMIZE = registerSoundEvent("origins.feline.phantomize");
    public static SoundEvent ORIGINS_UNDEAD_PHANTOMIZE = registerSoundEvent("origins.undead.phantomize");
    public static SoundEvent ORIGINS_WORM_SPLICE = registerSoundEvent("origins.worm.splice");
    public static SoundEvent ORIGINS_WORM_SCREECH = registerSoundEvent("origins.worm.screech");
    public static SoundEvent ORIGINS_SPEEDSTER_ZIP = registerSoundEvent("origins.speedster.zip");
    public static SoundEvent ORIGINS_SKYWARRIOR_TIME_STOP = registerSoundEvent("origins.skywarrior.time_stop");
    public static SoundEvent ORIGINS_SKYWARRIOR_CYROMANCER_SHOOT = registerSoundEvent("origins.skywarrior.cyromancer.shoot");
    public static SoundEvent ORIGINS_SKYWARRIOR_CYROMANCER_HIT = registerSoundEvent("origins.skywarrior.cyromancer.hit");
    public static SoundEvent ORIGINS_WIZARD_DOLPHIN = registerSoundEvent("origins.wizard.dolphin");
    public static SoundEvent ORIGINS_WIZARD_ELDRITCH_SHOOT = registerSoundEvent("origins.wizard.eldritch.shoot");
    public static SoundEvent ORIGINS_WIZARD_ELDRITCH_HIT = registerSoundEvent("origins.wizard.eldritch.hit");
    public static SoundEvent ORIGINS_WIZARD_BOOST = registerSoundEvent("origins.wizard.boost");
    public static SoundEvent ORIGINS_WIZARD_BLESSING = registerSoundEvent("origins.wizard.blessing");
    public static SoundEvent ORIGINS_STARBORN_PULL = registerSoundEvent("origins.starborn.pull");
    public static SoundEvent ORIGINS_STARBORN_EJECT = registerSoundEvent("origins.starborn.eject");
    public static SoundEvent ORIGINS_STARBORN_FURNACE = registerSoundEvent("origins.starborn.furnace");
    public static SoundEvent ORIGINS_STARBORN_SUPERNOVA = registerSoundEvent("origins.starborn.supernova");
    public static SoundEvent ORIGINS_STARBORN_LIFECYCLE = registerSoundEvent("origins.starborn.lifecycle");
    public static SoundEvent ORIGINS_INFERNIAN_FIREBALL = registerSoundEvent("origins.infernian.fireball");
    public static SoundEvent ORIGINS_INFERNIAN_CHARGE = registerSoundEvent("origins.infernian.charge");
    public static SoundEvent ORIGINS_INFERNIAN_CHARGE_LOST = registerSoundEvent("origins.infernian.charge_lost");
    public static SoundEvent ORIGINS_INFERNIAN_FIRE_CHARGE = registerSoundEvent("origins.infernian.fire_charge");




    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Gateway.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }


    public static void initializeSounds() {
        Gateway.LOGGER.info("Registering " + Gateway.MOD_ID + " Sounds");
    }
}
