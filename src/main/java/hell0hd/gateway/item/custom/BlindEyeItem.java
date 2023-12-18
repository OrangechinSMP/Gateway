package hell0hd.gateway.item.custom;

import hell0hd.gateway.sound.ModSounds;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import hell0hd.gateway.block.custom.ReinforcedDeepslateFrameBlock;
import hell0hd.gateway.block.ModBlocks;

public class BlindEyeItem extends Item {
    public BlindEyeItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(ModBlocks.REINFORCED_DEEPSLATE_FRAME) && !(Boolean)blockState.get(ReinforcedDeepslateFrameBlock.EYE)) {
            if (world.isClient) {
                return ActionResult.SUCCESS;
            } else {
                BlockState blockState2 = blockState.with(ReinforcedDeepslateFrameBlock.EYE, true);
                Block.pushEntitiesUpBeforeBlockChange(blockState, blockState2, world, blockPos);
                world.setBlockState(blockPos, blockState2, 2);
                world.updateComparators(blockPos, ModBlocks.REINFORCED_DEEPSLATE_FRAME);
                context.getStack().decrement(1);
                world.playSound(
                        null,
                        blockPos,
                        ModSounds.DEEP_DARK_PORTAL_FRAME_FILL,
                        SoundCategory.BLOCKS,
                        2f,
                        2f
                );
                BlockPattern.Result result = ReinforcedDeepslateFrameBlock.getCompletedFramePattern().searchAround(world, blockPos);
                if (result != null) {
                    BlockPos blockPos2 = result.getFrontTopLeft().add(6, 8, -12);

                    for(int i = 0; i < 1; ++i) {
                        for (int j = 0; j < 6; ++j) {
                            for (int k = 0; k < 20; ++k){
                                world.setBlockState(blockPos2.add(i, j, k), ModBlocks.CHARGING_GATEWAY.getDefaultState(), 2);
                            }
                        }
                    }

                    world.playSound(
                            null,
                            blockPos,
                            ModSounds.DEEP_DARK_PORTAL_SPAWN,
                            SoundCategory.BLOCKS,
                            2f,
                            2f
                    );

                }

                return ActionResult.CONSUME;
            }
        } else {
            return ActionResult.PASS;
        }
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.NONE);
        if (blockHitResult.getType() == HitResult.Type.BLOCK && world.getBlockState(blockHitResult.getBlockPos()).isOf(ModBlocks.REINFORCED_DEEPSLATE_FRAME)) {
            return TypedActionResult.pass(itemStack);
        } else {
            user.setCurrentHand(hand);
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld)world;
                BlockPos blockPos = serverWorld.locateStructure(StructureTags.EYE_OF_ENDER_LOCATED, user.getBlockPos(), 100, false);
                if (blockPos != null) {
                    EyeOfEnderEntity eyeOfEnderEntity = new EyeOfEnderEntity(world, user.getX(), user.getBodyY(0.5), user.getZ());
                    eyeOfEnderEntity.setItem(itemStack);
                    eyeOfEnderEntity.initTargetPos(blockPos);
                    world.emitGameEvent(GameEvent.PROJECTILE_SHOOT, eyeOfEnderEntity.getPos(), GameEvent.Emitter.of(user));
                    world.spawnEntity(eyeOfEnderEntity);
                    if (user instanceof ServerPlayerEntity) {
                        Criteria.USED_ENDER_EYE.trigger((ServerPlayerEntity)user, blockPos);
                    }

                    world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    world.syncWorldEvent((PlayerEntity)null, 1003, user.getBlockPos(), 0);
                    if (!user.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }

                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    user.swingHand(hand, true);
                    return TypedActionResult.success(itemStack);
                }
            }

            return TypedActionResult.consume(itemStack);
        }
    }
}
