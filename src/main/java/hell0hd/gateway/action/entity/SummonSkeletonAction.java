package hell0hd.gateway.action.entity;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.entity.custom.skeleton.OriginSkeletonEntity;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class SummonSkeletonAction {
    public static ActionFactory<Entity> createFactory() {
        return new ActionFactory<>(Gateway.id("summon_skeleton"), new SerializableData()
                .add("size", SerializableDataTypes.INT, 2),
                SummonSkeletonAction::summonSkeleton);
    }

    private static void summonSkeleton(SerializableData.Instance data, Entity entity) {
        OriginSkeletonEntity originSkeletonEntity = new OriginSkeletonEntity(entity.getWorld(), entity.getX(), entity.getY(), entity.getZ());
        if (entity instanceof PlayerEntity playerEntity) {
            originSkeletonEntity.setOwner(playerEntity);
        }
        entity.getWorld().spawnEntity(originSkeletonEntity);
    }
}
