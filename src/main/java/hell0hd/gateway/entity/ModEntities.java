package hell0hd.gateway.entity;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.entity.custom.CarmapoEntity;
import hell0hd.gateway.entity.custom.SuspiciousMassEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<CarmapoEntity> CARMAPO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Gateway.MOD_ID, "carmapo"),
            EntityType.Builder.create(CarmapoEntity::new, SpawnGroup.AMBIENT)
                    .dimensions(0.4f,1.1f).build());

    public static final EntityType<SuspiciousMassEntity> SUSPICIOUS_MASS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Gateway.MOD_ID, "suspicious_mass"),
            EntityType.Builder.<SuspiciousMassEntity>create(SuspiciousMassEntity::new, SpawnGroup.MISC)
                    .dimensions(0.4f,1.1f).build());

    public static void registerModEntities() {
        Gateway.LOGGER.info("carmapo lice, a rest, this man");
    }
}
