package hell0hd.gateway;

import hell0hd.gateway.block.ModBlocks;
import hell0hd.gateway.entity.ModEntities;
import hell0hd.gateway.entity.render.OriginSkeletonRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static hell0hd.gateway.Gateway.MOD_ID;

// heeeeelp theres a man in y house

public class GatewayClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.THE_GATEWAY, RenderLayer.getTranslucent());
        EntityRendererRegistry.register(ModEntities.ORIGIN_SKELETON, OriginSkeletonRenderer::new);


        ClientPlayNetworking.registerGlobalReceiver(
                new Identifier(MOD_ID, "yogurt"),
                (client, handler, buf, responseSender) -> {
                    client.inGameHud.setTitleTicks(50, 20, 100);
                    client.inGameHud.setTitle(Text.literal("\uE100"));
                }
        );
    }
}