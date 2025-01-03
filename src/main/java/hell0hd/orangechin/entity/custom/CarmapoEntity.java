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
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CarmapoEntity extends AnimalEntity implements Angerable {
    private int angerTime;
    @Nullable
    private UUID angryAt;
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angrySoundDelay;
    private static final UniformIntProvider ANGRY_SOUND_DELAY_RANGE;
    private static final UniformIntProvider ANGER_PASSING_COOLDOWN_RANGE;
    private int angerPassingCooldown;
    private static final Identifier ATTACKING_SPEED_MODIFIER_ID;
    private static final EntityAttributeModifier ATTACKING_SPEED_BOOST;

    public CarmapoEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new AnimalMateGoal(this, 1));
        this.goalSelector.add(2, new TemptGoal(this, 1, Ingredient.ofItems(Items.WHEAT_SEEDS), false));

        this.goalSelector.add(3, new FollowParentGoal(this, 1));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(4, new ActiveTargetGoal(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(1, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));
        this.targetSelector.add(3, new UniversalAngerGoal(this, true));

    }


    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, (double) 4)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, (double) 0.25f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 28);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.WHEAT_SEEDS);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.CARMAPO.create(world);
    }

    @Override
    public int getAngerTime() {
        return this.angerTime;
    }
    @Override
    public void setAngerTime(int angerTime) {
        this.angerTime = angerTime;
    }

    @Override
    @Nullable
    public UUID getAngryAt() {
        return this.angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }


    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    static {
        ATTACKING_SPEED_MODIFIER_ID = Identifier.ofVanilla("attacking");
        ATTACKING_SPEED_BOOST = new EntityAttributeModifier(ATTACKING_SPEED_MODIFIER_ID, 0.05, EntityAttributeModifier.Operation.ADD_VALUE);
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
        ANGRY_SOUND_DELAY_RANGE = TimeHelper.betweenSeconds(0, 1);
        ANGER_PASSING_COOLDOWN_RANGE = TimeHelper.betweenSeconds(4, 6);
    }

    protected void mobTick() {
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (this.hasAngerTime()) {
            if (!this.isBaby() && !entityAttributeInstance.hasModifier(ATTACKING_SPEED_MODIFIER_ID)) {
                entityAttributeInstance.addTemporaryModifier(ATTACKING_SPEED_BOOST);
            }

            this.tickAngrySound();
        } else if (entityAttributeInstance.hasModifier(ATTACKING_SPEED_MODIFIER_ID)) {
            entityAttributeInstance.removeModifier(ATTACKING_SPEED_MODIFIER_ID);
        }

        this.tickAngerLogic((ServerWorld)this.getWorld(), true);
        if (this.getTarget() != null) {
            this.tickAngerPassing();
        }

        if (this.hasAngerTime()) {
            this.playerHitTimer = this.age;
        }

        super.mobTick();
    }

    private void tickAngrySound() {
        if (this.angrySoundDelay > 0) {
            --this.angrySoundDelay;
            if (this.angrySoundDelay == 0) {
                this.playAngrySound();
            }
        }

    }

    private void tickAngerPassing() {
        if (this.angerPassingCooldown > 0) {
            --this.angerPassingCooldown;
        } else {
            if (this.getVisibilityCache().canSee(this.getTarget())) {
                this.angerNearbyCarmapos();
            }

            this.angerPassingCooldown = ANGER_PASSING_COOLDOWN_RANGE.get(this.random);
        }
    }

    private void angerNearbyCarmapos() {
        double d = this.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
        Box box = Box.from(this.getPos()).expand(d, (double)10.0F, d);
        this.getWorld().getEntitiesByClass(CarmapoEntity.class, box, EntityPredicates.EXCEPT_SPECTATOR).stream().filter((carmapo) -> carmapo != this).filter((carmapo) -> carmapo.getTarget() == null).filter((carmapo) -> !carmapo.isTeammate(this.getTarget())).forEach((carmapo) -> carmapo.setTarget(this.getTarget()));
    }

    private void playAngrySound() {
        this.playSound(SoundEvents.ENTITY_ZOMBIFIED_PIGLIN_ANGRY, this.getSoundVolume() * 2.0F, this.getSoundPitch() * 1.8F);
    }

    public void setTarget(@Nullable LivingEntity target) {
        if (this.getTarget() == null && target != null) {
            this.angrySoundDelay = ANGRY_SOUND_DELAY_RANGE.get(this.random);
            this.angerPassingCooldown = ANGER_PASSING_COOLDOWN_RANGE.get(this.random);
        }

        if (target instanceof PlayerEntity) {
            this.setAttacking((PlayerEntity)target);
        }

        super.setTarget(target);
    }
}
