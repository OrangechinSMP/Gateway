package hell0hd.orangechin.entity;

import hell0hd.orangechin.Orangechin;
import hell0hd.orangechin.entity.custom.CarmapoEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<CarmapoEntity> CARMAPO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Orangechin.MOD_ID, "carmapo"),
            EntityType.Builder.create(CarmapoEntity::new, SpawnGroup.AMBIENT)
                    .dimensions(0.4f,1.1f).build());

    public static void registerModEntities() {
        Orangechin.LOGGER.info("carmapo lice, a rest, this man");
    }
}
