package hell0hd.gateway.potion;

import hell0hd.gateway.Gateway;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final RegistryEntry<Potion> ABSORPTION = registerPotion("absorption",
            new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 0)));
    public static final RegistryEntry<Potion> LONG_ABSORPTION = registerPotion("long_absorption",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, 1800, 0)));
    public static final RegistryEntry<Potion> STRONG_ABSORPTION = registerPotion("strong_absorption",
            new Potion("absorption", new StatusEffectInstance(StatusEffects.ABSORPTION, 900, 1)));

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(Gateway.MOD_ID, name), potion);
    }
    public static void registerPotions() {
        Gateway.LOGGER.info("Kaupenjoe my goat <3");
    }
}
