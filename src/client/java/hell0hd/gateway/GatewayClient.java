package hell0hd.gateway;

import com.mojang.blaze3d.systems.RenderSystem;
import hell0hd.gateway.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.*;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

// heeeeelp theres a man in y house

public class GatewayClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.THE_GATEWAY, RenderLayer.getTranslucent());

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            Matrix4f positionMatrix = drawContext.getMatrices().peek().getPositionMatrix();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
            buffer.vertex(positionMatrix, 0, 0, 0).color(1f, 1f, 1f, 1f).texture(0f, 0f).next();
            buffer.vertex(positionMatrix, 0, drawContext.getScaledWindowHeight(), 0).color(1f, 1f, 1f, 1f).texture(0f, 1f).next();
            buffer.vertex(positionMatrix, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), 0).color(1f, 1f, 1f, 1f).texture(1f, 1f).next();
            buffer.vertex(positionMatrix, drawContext.getScaledWindowWidth(), 0, 0).color(1f, 1f, 1f, 1f).texture(1f, 0f).next();

            RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
            RenderSystem.setShaderTexture(0, new Identifier(Gateway.MOD_ID, "heaven.png"));
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            RenderSystem.depthFunc(GL11.GL_ALWAYS);

            tessellator.draw();

            RenderSystem.depthFunc(GL11.GL_LEQUAL);
        });
    }
}