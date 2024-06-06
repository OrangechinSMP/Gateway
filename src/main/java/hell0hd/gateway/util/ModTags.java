package hell0hd.gateway.util;

import hell0hd.gateway.Gateway;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

public class ModTags {
    public static class Structures {
        public static final TagKey<Structure> ANCIENT_CITIES =
                createTag("ancient_cities");


        private static TagKey<Structure> createTag(String name) {
            return TagKey.of(RegistryKeys.STRUCTURE, new Identifier(Gateway.MOD_ID, name));
        }
    }
}
