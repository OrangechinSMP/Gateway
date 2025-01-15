package hell0hd.gateway.entity.custom;

import hell0hd.gateway.entity.ModEntities;
import hell0hd.gateway.item.ModItems;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class SuspiciousMassEntity extends ThrownItemEntity {
    private static final EntityDimensions EMPTY_DIMENSIONS = EntityDimensions.fixed(0.0F, 0.0F);

    public SuspiciousMassEntity(EntityType<? extends SuspiciousMassEntity> entityType, World world) {
        super(entityType, world);
    }

    public SuspiciousMassEntity(World world, double x, double y, double z) {
        super(ModEntities.SUSPICIOUS_MASS, x, y, z, world);
    }

    public SuspiciousMassEntity(World world, LivingEntity owner) {
        super(ModEntities.SUSPICIOUS_MASS, owner, world);
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            if (this.random.nextInt(8) == 0) {
                int i = 1;
                if (this.random.nextInt(32) == 0) {
                    i = 4;
                }

                for (int j = 0; j < i; j++) {
                    CarmapoEntity carmapoEntity = ModEntities.CARMAPO.create(this.getWorld());
                    if (carmapoEntity != null) {
                        carmapoEntity.setBreedingAge(-24000);
                        carmapoEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                        if (!carmapoEntity.recalculateDimensions(EMPTY_DIMENSIONS)) {
                            break;
                        }

                        this.getWorld().spawnEntity(carmapoEntity);
                    }
                }
            }

            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SUSPICIOUS_MASS;
    }
}
