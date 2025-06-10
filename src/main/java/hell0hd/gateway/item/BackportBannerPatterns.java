package hell0hd.gateway.item;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class BackportBannerPatterns {
    static TagKey<BannerPattern> FIELD_MASONED_PATTERN_ITEM = of("pattern_item/field_masoned");
    static TagKey<BannerPattern> BORDURE_INDENTED_PATTERN_ITEM = of("pattern_item/bordure_indented");


    private static TagKey<BannerPattern> of(String id) {
        return TagKey.of(RegistryKeys.BANNER_PATTERN, Identifier.of("minecraft", id));
    }
}
