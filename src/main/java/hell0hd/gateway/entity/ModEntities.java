package hell0hd.gateway.entity;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.entity.custom.skeleton.OriginSkeletonEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static EntityType<OriginSkeletonEntity> ORIGIN_SKELETON = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Gateway.MOD_ID, "origin_skeleton"),
            FabricEntityTypeBuilder.<OriginSkeletonEntity>create(SpawnGroup.MONSTER, OriginSkeletonEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f,1.99f))
                    .trackRangeBlocks(8)
                    .build()
    );
    public static void init() {
        FabricDefaultAttributeRegistry.register(ModEntities.ORIGIN_SKELETON, HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25));
    }
}
