package hell0hd.gateway;

import hell0hd.gateway.entity.ModEntities;
import hell0hd.gateway.entity.client.CarmapoModel;
import hell0hd.gateway.entity.client.CarmapoRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class GatewayClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(CarmapoModel.CARMAPO, CarmapoModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CARMAPO, CarmapoRenderer::new);
        EntityRendererRegistry.register(ModEntities.SUSPICIOUS_MASS, FlyingItemEntityRenderer::new);
    }
}
