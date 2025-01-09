package hell0hd.orangechin.entity.custom;


import hell0hd.orangechin.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
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
import net.minecraft.util.Identifier;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CarmapoEntity extends AnimalEntity implements Angerable {
    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(CarmapoEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    @Nullable
    private UUID angryAt;
    private static final Identifier ATTACKING_SPEED_MODIFIER_ID = Identifier.ofVanilla("attacking");
    private static final EntityAttributeModifier ATTACKING_SPEED_BOOST = new EntityAttributeModifier(
            ATTACKING_SPEED_MODIFIER_ID, 0.05, EntityAttributeModifier.Operation.ADD_VALUE
    );
    private int angerPassingCooldown;
    private static final UniformIntProvider ANGER_PASSING_COOLDOWN_RANGE = TimeHelper.betweenSeconds(0, 0);

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

        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0, false));
        this.targetSelector.add(8, new UniversalAngerGoal<>(this, true));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));

    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, (double) 4)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, (double) 0.25f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 28);
    }

    @Override
    protected void mobTick() {
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (this.hasAngerTime()) {
            if (!this.isBaby() && !entityAttributeInstance.hasModifier(ATTACKING_SPEED_MODIFIER_ID)) {
                entityAttributeInstance.addTemporaryModifier(ATTACKING_SPEED_BOOST);
            }
        }

        if (this.hasAngerTime()) {
            this.playerHitTimer = this.age;
        }
        

        super.mobTick();
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
                .getEntitiesByClass(CarmapoEntity.class, box, EntityPredicates.EXCEPT_SPECTATOR)
                .stream()
                .filter(carmapo -> carmapo != this)
                .filter(carmapo -> carmapo.getTarget() == null)
                .filter(carmapo -> !carmapo.isTeammate(this.getTarget()))
                .forEach(carmapo -> carmapo.setTarget(this.getTarget()));
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if (this.getTarget() == null && target != null) {
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
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.writeAngerToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.readAngerFromNbt(this.getWorld(), nbt);
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
            if (attacker instanceof LivingEntity livingAttacker && !this.getWorld().isClient) {
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
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.CARMAPO.create(world);
    }








}
