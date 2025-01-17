package hell0hd.gateway.mixin;


import hell0hd.gateway.entity.ModEntities;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @Inject(at = @At("HEAD"), method = "addFarmAnimals(Lnet/minecraft/world/biome/SpawnSettings$Builder;)V")
    private static void injectAddFarmAnimals(SpawnSettings.Builder builder, CallbackInfo info) {
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.CARMAPO, 12, 4, 4));

    }
}