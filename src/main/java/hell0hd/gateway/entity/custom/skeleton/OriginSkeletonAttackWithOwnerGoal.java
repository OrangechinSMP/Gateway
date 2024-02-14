package hell0hd.gateway.entity.custom.skeleton;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;

import java.util.EnumSet;

public class OriginSkeletonAttackWithOwnerGoal extends TrackTargetGoal {
    private final OriginSkeletonEntity originSkeletonEntity;
    private LivingEntity attacking;
    private int lastAttackTime;

    public OriginSkeletonAttackWithOwnerGoal(OriginSkeletonEntity originSkeletonEntity) {
        super(originSkeletonEntity, false);
        this.originSkeletonEntity = originSkeletonEntity;
        this.setControls(EnumSet.of(Control.TARGET));
    }

    public boolean canStart() {
        LivingEntity livingEntity = this.originSkeletonEntity.getOwner();
        if (livingEntity == null) {
            return false;
        } else {
            this.attacking = livingEntity.getAttacking();
            int i = livingEntity.getLastAttackTime();
            return i != this.lastAttackTime && this.canTrack(this.attacking, TargetPredicate.DEFAULT) && this.originSkeletonEntity.canAttackWithOwner(this.attacking, livingEntity);
        }
    }

    public void start() {
        this.mob.setTarget(this.attacking);
        LivingEntity livingEntity = this.originSkeletonEntity.getOwner();
        if (livingEntity != null) {
            this.lastAttackTime = livingEntity.getLastAttackTime();
        }

        super.start();
    }
}