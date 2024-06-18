package hell0hd.gateway.item.custom;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.Scheduler;
import hell0hd.gateway.block.ModBlocks;
import hell0hd.gateway.block.custom.ReinforcedDeepslateFrameBlock;
import hell0hd.gateway.entity.custom.blindeye.BlindEyeEntity;
import hell0hd.gateway.sound.ModSounds;
import hell0hd.gateway.util.ModTags;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.frozenblock.lib.screenshake.api.ScreenShakeManager;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.concurrent.TimeUnit;

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

                if (!context.getPlayer().isCreative()) {
                    context.getStack().decrement(1);
                }

                world.playSound(
                        null,
                        blockPos,
                        ModSounds.DEEP_DARK_PORTAL_FRAME_FILL,
                        SoundCategory.BLOCKS,
                        1f,
                        1f
                );
                BlockPattern.Result result = ReinforcedDeepslateFrameBlock.getCompletedFramePattern().searchAround(world, blockPos);
                if (result != null) {
                    BlockPos blockPos2 = result.getFrontTopLeft().add(-12, 8, -6);

                    for(int i = 0; i < 20; ++i) {
                        for (int j = 0; j < 6; ++j) {
                            for (int k = 0; k < 1; ++k){
                                world.setBlockState(blockPos2.add(i, j, k), ModBlocks.THE_GATEWAY.getDefaultState(), 2);
                            }
                        }
                    }

                    world.playSound(
                            null,
                            blockPos,
                            ModSounds.DEEP_DARK_PORTAL_SPAWN,
                            SoundCategory.BLOCKS,
                            62.5f,
                            1f
                    );

                    Gateway.INSTANCE.getPlayerManager().getPlayerList().forEach(player -> {
                        ScreenShakeManager.addEntityScreenShake(
                                player,
                                2.0f,
                                300,
                                100,
                                1000.0f
                        );

                        Scheduler.schedule(() -> {
                            ServerPlayNetworking.send(
                                    player, new Identifier(Gateway.MOD_ID, "yogurt"), PacketByteBufs.empty()
                            );
                        }, 7500, TimeUnit.MILLISECONDS);
                    });



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
                BlockPos blockPos = serverWorld.locateStructure(ModTags.Structures.ANCIENT_CITIES, user.getBlockPos(), 100, false);
                if (blockPos != null) {
                    BlindEyeEntity blindEyeEntity = new BlindEyeEntity(world, user.getX(), user.getBodyY(0.5), user.getZ());
                    blindEyeEntity.setItem(itemStack);
                    blindEyeEntity.initTargetPos(blockPos);
                    world.emitGameEvent(GameEvent.PROJECTILE_SHOOT, blindEyeEntity.getPos(), GameEvent.Emitter.of(user));
                    world.spawnEntity(blindEyeEntity);
                    if (user instanceof ServerPlayerEntity) {
                        Criteria.USED_ENDER_EYE.trigger((ServerPlayerEntity)user, blockPos);
                    }

                    world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), ModSounds.ENTITY_BLINDEYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 1.0f / (world.getRandom().nextFloat() * 0.4F + 0.8F));
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
