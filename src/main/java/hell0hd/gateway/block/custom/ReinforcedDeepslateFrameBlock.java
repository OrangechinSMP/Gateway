package hell0hd.gateway.block.custom;

import com.google.common.base.Predicates;
import hell0hd.gateway.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ReinforcedDeepslateFrameBlock extends Block {
    public static final DirectionProperty FACING;
    public static final BooleanProperty EYE;
    protected static final VoxelShape FRAME_SHAPE;
    protected static final VoxelShape EYE_SHAPE;
    protected static final VoxelShape FRAME_WITH_EYE_SHAPE;
    private static BlockPattern COMPLETED_FRAME;

    public ReinforcedDeepslateFrameBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(EYE, false));
    }

    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Boolean)state.get(EYE) ? FRAME_WITH_EYE_SHAPE : FRAME_SHAPE;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)((BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())).with(EYE, false);
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return (Boolean)state.get(EYE) ? 15 : 0;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, EYE});
    }

    public static BlockPattern getCompletedFramePattern() {
        if (COMPLETED_FRAME == null) {
            COMPLETED_FRAME = BlockPatternBuilder.start()
                    .aisle("v?v?v?v")
                    .where('?', CachedBlockPosition.matchesBlockState(BlockStatePredicate.ANY))
                    .where('v', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(ModBlocks.REINFORCED_DEEPSLATE_FRAME).with(EYE, Predicates.equalTo(true))))
                    .build();
        }

        return COMPLETED_FRAME;
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        EYE = Properties.EYE;
        FRAME_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 13.0, 16.0);
        EYE_SHAPE = Block.createCuboidShape(4.0, 13.0, 4.0, 12.0, 16.0, 12.0);
        FRAME_WITH_EYE_SHAPE = VoxelShapes.union(FRAME_SHAPE, EYE_SHAPE);
    }
}
