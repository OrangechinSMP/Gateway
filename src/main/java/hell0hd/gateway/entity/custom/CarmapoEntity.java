package hell0hd.gateway.entity.custom;


import hell0hd.gateway.entity.ModEntities;
import hell0hd.gateway.item.ModItems;
import hell0hd.gateway.sounds.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

public class CarmapoEntity extends AnimalEntity implements Angerable {
    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(CarmapoEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    @Nullable
    private UUID angryAt;
    private int angerPassingCooldown;
    private static final UniformIntProvider ANGER_PASSING_COOLDOWN_RANGE = TimeHelper.betweenSeconds(0, 0);
    private static final TrackedData<Boolean> AGGRESSIVE = DataTracker.registerData(CarmapoEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public int massLayTime = this.random.nextInt(6000) + 6000;


    public CarmapoEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new AnimalMateGoal(this, 1));
        this.goalSelector.add(2, new TemptGoal(this, 1, Ingredient.ofItems(Items.WHEAT_SEEDS), false));

        this.goalSelector.add(3, new FollowParentGoal(this, 1));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAroundGoal(this));

        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.35, false));
        this.targetSelector.add(8, new UniversalAngerGoal<>(this, true));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));

    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 28);
    }


    public boolean isAggressive() {
        return this.getDataTracker().get(AGGRESSIVE);
    }

    public static boolean shouldBeAggressive(net.minecraft.util.math.random.Random random) {
        return random.nextFloat() < 0.05F;
    }

    public record CarmapoData(boolean aggressive) implements EntityData {
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        net.minecraft.util.math.random.Random random = world.getRandom();
        entityData = super.initialize(world, difficulty, spawnReason, entityData);
        entityData = new CarmapoEntity.CarmapoData(shouldBeAggressive(random));

        if (entityData instanceof CarmapoData(boolean aggressive)) {
            if (aggressive) {
                this.setAggressive(true);
            }
        }
        return entityData;
    }


    private void tickAngerPassing() {
        if (this.angerPassingCooldown > 0) {
            this.angerPassingCooldown--;
        } else {
            if (this.getVisibilityCache().canSee(this.getTarget())) {
                this.angerNearbyCarmapos();
            }

            this.angerPassingCooldown = ANGER_PASSING_COOLDOWN_RANGE.get(this.random);
        }
    }


    private void angerNearbyCarmapos() {
        double d = this.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
        Box box = Box.from(this.getPos()).expand(d, 10.0, d);
            this.getWorld()
                    .getEntitiesByClass(CarmapoEntity.class, box, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR)
                    .stream()
                    .filter(carmapo -> carmapo != this)
                    .filter(carmapo -> carmapo.getTarget() == null)
                    .filter(carmapo -> carmapo.isAggressive())
                    .filter(carmapo -> !carmapo.isTeammate(this.getTarget()))
                    .forEach(carmapo -> carmapo.setTarget(this.getTarget()));
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if (this.getTarget() == null && target != null ) {
            this.angerPassingCooldown = ANGER_PASSING_COOLDOWN_RANGE.get(this.random);
        }

        if (target instanceof PlayerEntity) {
            this.setAttacking((PlayerEntity)target);
        }

        super.setTarget(target);
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ANGER_TIME, 0);
        builder.add(AGGRESSIVE, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.writeAngerToNbt(nbt);
        nbt.putBoolean("isAggressive", this.isAggressive());
        nbt.putInt("massLayTime", this.massLayTime);
    }

    public void setAggressive(boolean aggressive) {
        this.getDataTracker().set(AGGRESSIVE, aggressive);
        }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.readAngerFromNbt(this.getWorld(), nbt);
        this.setAggressive(nbt.getBoolean("isAggressive"));
        if (nbt.contains("massLayTime")) {
            this.massLayTime = nbt.getInt("massLayTime");
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (!this.getWorld().isClient) {
            this.tickAngerLogic((ServerWorld)this.getWorld(), true);
            if (this.getTarget() != null) {
                this.tickAngerPassing();
            }
        }
        if (!this.getWorld().isClient && this.isAlive() && !this.isBaby() && --this.massLayTime <= 0) {
            this.playSound(ModSounds.ENTITY_CARMAPO_MASS, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.dropItem(ModItems.SUSPICIOUS_MASS);
            this.emitGameEvent(GameEvent.ENTITY_PLACE);
            this.massLayTime = this.random.nextInt(6000) + 6000;
        }
    }

    @Override
    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    @Override
    public void setAngerTime(int angerTime) {
        this.dataTracker.set(ANGER_TIME, angerTime);
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (super.damage(source, amount)) {
            Entity attacker = source.getAttacker();
            if (attacker instanceof LivingEntity livingAttacker && !this.getWorld().isClient && this.isAggressive()){
                this.setAngryAt(livingAttacker.getUuid());
                this.chooseRandomAngerTime();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldAngerAt(LivingEntity entity) {
        if (!this.canTarget(entity)) {
            return false;
        }
        return entity.getUuid().equals(this.getAngryAt());
    }


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.WHEAT_SEEDS);
    }


    @Override
    public @Nullable AnimalEntity createChild(ServerWorld world, PassiveEntity entity) {
        Random rnd = new Random();
        int i = rnd.nextInt(100);
        CarmapoEntity baby = ModEntities.CARMAPO.create(world);
        if(i < 5){
            baby.setAggressive(true);
        }



        return baby;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.hasAngerTime()) {
            return ModSounds.ENTITY_CARMAPO_GROWL;
        } else {
            return ModSounds.ENTITY_CARMAPO_AMBIENT;
        }
    }



    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PIGLIN_BRUTE_STEP, 0.15F, 1.0F);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_CARMAPO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_CARMAPO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }











}
