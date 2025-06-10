package hell0hd.gateway.item;

import hell0hd.gateway.Gateway;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBannerPatterns {

    private static RegistryKey<BannerPattern> of(String id) {
        return RegistryKey.of(RegistryKeys.BANNER_PATTERN, Identifier.of(Gateway.MOD_ID, id));
    }
}
