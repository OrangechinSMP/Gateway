package hell0hd.gateway.block.custom.gateway;

import hell0hd.gateway.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TheGatewayBlockEntity extends BlockEntity {

    private int charge = 0;

    public TheGatewayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.THE_GATEWAY_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("charge", charge);

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        charge = nbt.getInt("charge");
    }

    public static void tick(World world, BlockPos pos, BlockState state, TheGatewayBlockEntity be) {
        if (be.charge < 220) {
            be.charge++;
        }

        if (be.charge >= 220) {
            if (!state.get(TheGatewayBlock.CHARGED)) {
                world.setBlockState(pos, state.with(TheGatewayBlock.CHARGED, true));
            }
        }
    }
}
