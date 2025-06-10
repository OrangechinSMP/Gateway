package hell0hd.gateway;

import hell0hd.gateway.block.ModBlocks;
import hell0hd.gateway.entity.ModEntities;
import hell0hd.gateway.entity.client.CarmapoModel;
import hell0hd.gateway.entity.client.CarmapoRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class GatewayClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(CarmapoModel.CARMAPO, CarmapoModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CARMAPO, CarmapoRenderer::new);
        EntityRendererRegistry.register(ModEntities.SUSPICIOUS_MASS, FlyingItemEntityRenderer::new);

    }
}
