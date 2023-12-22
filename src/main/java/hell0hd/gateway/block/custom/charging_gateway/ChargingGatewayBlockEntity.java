package hell0hd.gateway.block.custom.charging_gateway;

import hell0hd.gateway.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChargingGatewayBlockEntity extends BlockEntity {

    private static int charge = 0;

    public ChargingGatewayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.CHARGING_GATEWAY_ENTITY, pos, state);
    }

    public static void tick(World world1, BlockPos pos, BlockState state1, BlockEntity be) {
        ++charge;

        if (charge == 100) {
            world1.setBlockState(pos, ModBlocks.THE_GATEWAY.getDefaultState());
        }
    }
}
