package hell0hd.gateway.entity;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.entity.custom.WaterboltEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import javax.swing.text.html.parser.Entity;

public class ModEntities {

    public static final EntityType<WaterboltEntity> WATERBOLT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Gateway.MOD_ID, "waterbolt"),
            FabricEntityTypeBuilder.<WaterboltEntity>create(SpawnGroup.MISC, WaterboltEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static void registerModEntities() {
        Gateway.LOGGER.info("Registering Mod Items for " + Gateway.MOD_ID);
    }
}
