package hell0hd.gateway.entity.custom.blindeye;

import hell0hd.gateway.entity.ModEntities;
import hell0hd.gateway.item.ModItems;
import hell0hd.gateway.sound.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

public class BlindEyeEntity extends Entity
        implements FlyingItemEntity {
    private static final TrackedData<ItemStack> ITEM = DataTracker.registerData(EyeOfEnderEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    private double targetX;
    private double targetY;
    private double targetZ;
    private int lifespan;
    private boolean dropsItem;

    public BlindEyeEntity(EntityType<? extends BlindEyeEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlindEyeEntity(World world, double x, double y, double z) {
        this((EntityType<? extends BlindEyeEntity>)ModEntities.BLIND_EYE, world);
        this.setPosition(x, y, z);
    }

    public void setItem(ItemStack stack) {
        if (!stack.isOf(Items.ENDER_EYE) || stack.hasNbt()) {
            this.getDataTracker().set(ITEM, stack.copyWithCount(1));
        }
    }

    private ItemStack getTrackedItem() {
        return this.getDataTracker().get(ITEM);
    }

    @Override
    public ItemStack getStack() {
        ItemStack itemStack = this.getTrackedItem();
        return itemStack.isEmpty() ? new ItemStack(Items.ENDER_EYE) : itemStack;
    }

    @Override
    protected void initDataTracker() {
        this.getDataTracker().startTracking(ITEM, ItemStack.EMPTY);
    }

    @Override
    public boolean shouldRender(double distance) {
        double d = this.getBoundingBox().getAverageSideLength() * 4.0;
        if (Double.isNaN(d)) {
            d = 4.0;
        }
        return distance < (d *= 64.0) * d;
    }

    /**
     * Sets where the eye will fly towards.
     * If close enough, it will fly directly towards it, otherwise, it will fly upwards, in the direction of the BlockPos.
     *
     * @param pos the block the eye of ender is drawn towards
     */
    public void initTargetPos(BlockPos pos) {
        double g;
        double d = pos.getX();
        int i = pos.getY();
        double e = pos.getZ();
        double f = d - this.getX();
        double h = Math.sqrt(f * f + (g = e - this.getZ()) * g);
        if (h > 12.0) {
            this.targetX = this.getX() + f / h * 12.0;
            this.targetZ = this.getZ() + g / h * 12.0;
            this.targetY = this.getY() + 8.0;
        } else {
            this.targetX = d;
            this.targetY = i;
            this.targetZ = e;
        }
        this.lifespan = 0;
        this.dropsItem = this.random.nextInt(5) > 0;
    }


    @Override
    public void setVelocityClient(double x, double y, double z) {
        this.setVelocity(x, y, z);
        if (this.prevPitch == 0.0f && this.prevYaw == 0.0f) {
            double d = Math.sqrt(x * x + z * z);
            this.setYaw((float)(MathHelper.atan2(x, z) * 57.2957763671875));
            this.setPitch((float)(MathHelper.atan2(y, d) * 57.2957763671875));
            this.prevYaw = this.getYaw();
            this.prevPitch = this.getPitch();
        }
    }
    @Override
    public void tick() {
        super.tick();
        Vec3d vec3d = this.getVelocity();
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        double g = vec3d.horizontalLength();
        this.setPitch(ProjectileEntity.updateRotation(this.prevPitch, (float)(MathHelper.atan2(vec3d.y, g) * 57.2957763671875)));
        this.setYaw(ProjectileEntity.updateRotation(this.prevYaw, (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875)));
        if (!this.getWorld().isClient) {
            double h = this.targetX - d;
            double i = this.targetZ - f;
            float j = (float)Math.sqrt(h * h + i * i);
            float k = (float)MathHelper.atan2(i, h);
            double l = MathHelper.lerp(0.0025, g, (double)j);
            double m = vec3d.y;
            if (j < 1.0f) {
                l *= 0.8;
                m *= 0.8;
            }
            int n = this.getY() < this.targetY ? 1 : -1;
            vec3d = new Vec3d(Math.cos(k) * l, m + ((double)n - m) * (double)0.015f, Math.sin(k) * l);
            this.setVelocity(vec3d);
        }
        float o = 0.25f;
        if (this.isTouchingWater()) {
            for (int p = 0; p < 4; ++p) {
                this.getWorld().addParticle(ParticleTypes.SCULK_CHARGE_POP, d - vec3d.x * 0.25, e - vec3d.y * 0.25, f - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z);
            }
        } else {
            this.getWorld().addParticle(ParticleTypes.SCULK_SOUL, d - vec3d.x * 0.25 + this.random.nextDouble() * 0.6 - 0.3, e - vec3d.y * 0.25 - 0.5, f - vec3d.z * 0.25 + this.random.nextDouble() * 0.6 - 0.3, vec3d.x, vec3d.y, vec3d.z);
        }
        if (!this.getWorld().isClient) {
            this.setPosition(d, e, f);
            ++this.lifespan;
            if (this.lifespan > 80 && !this.getWorld().isClient) {
                this.discard();
                if (this.dropsItem) {
                    this.getWorld().spawnEntity(new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), this.getStack()));
                    this.playSound(ModSounds.ENTITY_BLINDEYE_DEATH, 1.0f, 1.0f);
                } else {
                    this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, getStack()), this.getX() + this.getRotationVector().x / 2.0, this.getY(), this.getZ() + this.getRotationVector().z / 2.0, vec3d.x, vec3d.y + 0.05, vec3d.z);
                    this.playSound(ModSounds.ENTITY_BLINDEYE_DEATH, 1.0f, 0.5f);
                    this.getWorld().addParticle(ParticleTypes.SCULK_SOUL, d - vec3d.x * 0.25 + this.random.nextDouble() * 0.6 - 0.3, e - vec3d.y * 0.25 - 0.5, f - vec3d.z * 0.25 + this.random.nextDouble() * 0.6 - 0.3, vec3d.x, vec3d.y, vec3d.z);
                    this.getWorld().addParticle(ParticleTypes.SCULK_CHARGE_POP, d - vec3d.x * 0.25 + this.random.nextDouble() * 0.6 - 0.3, e - vec3d.y * 0.25 - 0.5, f - vec3d.z * 0.25 + this.random.nextDouble() * 0.6 - 0.3, vec3d.x, vec3d.y, vec3d.z);
                    this.getWorld().addParticle(ParticleTypes.POOF, d - vec3d.x * 0.25 + this.random.nextDouble() * 0.6 - 0.3, e - vec3d.y * 0.25 - 0.5, f - vec3d.z * 0.25 + this.random.nextDouble() * 0.6 - 0.3, vec3d.x, vec3d.y, vec3d.z);
                }
            }
        } else {
            this.setPos(d, e, f);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        ItemStack itemStack = this.getTrackedItem();
        if (!itemStack.isEmpty()) {
            nbt.put("Item", itemStack.writeNbt(new NbtCompound()));
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("Item"));
        this.setItem(itemStack);
    }

    @Override
    public float getBrightnessAtEyes() {
        return 1.0f;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }
}


