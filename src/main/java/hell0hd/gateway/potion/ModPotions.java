package hell0hd.gateway.potion;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.item.ModItems;
import hell0hd.gateway.mixin.BrewingRecipeRegistryMixin;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static Potion DARKNESS_POTION;
    public static Potion LONG_DARKNESS_POTION;

    private static Potion register(String name, Potion potion) {
        return Registry.register(Registries.POTION, new Identifier(Gateway.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        DARKNESS_POTION = register("darkness_potion", new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 200)));
        LONG_DARKNESS_POTION = register("long_darkness_potion", new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 400)));
        registerPotionRecipes();
    }
    private static void registerPotionRecipes(){
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, ModItems.SCULK_POWDER, ModPotions.DARKNESS_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.DARKNESS_POTION, Items.REDSTONE, ModPotions.LONG_DARKNESS_POTION);
    }

}
