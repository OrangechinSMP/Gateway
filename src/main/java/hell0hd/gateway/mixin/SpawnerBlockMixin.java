package hell0hd.gateway.mixin;

import hell0hd.gateway.sounds.ModSounds;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SpawnerBlock.class)
public abstract class SpawnerBlockMixin extends Block {

    public SpawnerBlockMixin(Settings settings) {
        super(settings);
    }

    @ModifyArg(method="<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockWithEntity;<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V"))
    private static BlockWithEntity.Settings gateway$SpawnerSounds(BlockWithEntity.Settings settings) {

        // Allows players to break end portal frame blocks in the same time as obsidian, by adjusting
        // the hardness to that of obsidian but leaving the resistance like end portal frame block.
        return settings.sounds(ModSounds.SPAWNER);
    }
}
