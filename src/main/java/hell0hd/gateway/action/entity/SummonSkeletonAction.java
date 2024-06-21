package hell0hd.gateway.action.entity;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.entity.custom.skeleton.OriginSkeletonEntity;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

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
            originSkeletonEntity.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.GOLDEN_SWORD));
            originSkeletonEntity.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.SHIELD));
            originSkeletonEntity.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
            originSkeletonEntity.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0);
            originSkeletonEntity.setEquipmentDropChance(EquipmentSlot.OFFHAND, 0);
            originSkeletonEntity.setEquipmentDropChance(EquipmentSlot.HEAD, 0);
        }
        entity.getWorld().spawnEntity(originSkeletonEntity);
    }
}
