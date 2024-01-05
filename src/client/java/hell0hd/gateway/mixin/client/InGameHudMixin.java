package hell0hd.gateway.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow @Nullable private Text title;

    @Shadow private int titleRemainTicks;

    @Shadow @Final private MinecraftClient client;
    @Shadow
    private int titleFadeOutTicks;

    @Shadow private int titleStayTicks;

    @Shadow private int titleFadeInTicks;

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow protected abstract void drawTextBackground(DrawContext context, TextRenderer textRenderer, int yOffset, int width, int color);

    @Shadow @Nullable private Text subtitle;

    @Inject(
            method = "render",
            at = @At(target = "net/minecraft/client/MinecraftClient.getLastFrameDuration ()F", value = "INVOKE", opcode = Opcodes.INVOKEVIRTUAL)
    )
    public void gateway$handleHud(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (this.title != null && this.titleRemainTicks > 0) {
            this.client.getProfiler().push("titleAndSubtitle");
            int l = getL(tickDelta);
            if (l > 8) {
                context.getMatrices().push();
                context.getMatrices().translate((float)(this.scaledWidth / 2), (float)(this.scaledHeight / 2), 0.0F);
                RenderSystem.enableBlend();
                RenderSystem.depthFunc(GL11.GL_ALWAYS);
                context.getMatrices().push();
                context.getMatrices().scale(4.0F, 4.0F, 4.0F);
                int k = l << 24 & -16777216;
                int m = getTextRenderer().getWidth(this.title);
                this.drawTextBackground(context, getTextRenderer(), -10, m, 16777215 | k);
                context.drawTextWithShadow(getTextRenderer(), this.title, -m / 2, -10, 16777215 | k);
                context.getMatrices().pop();
                if (this.subtitle != null) {
                    context.getMatrices().push();
                    context.getMatrices().scale(2.0F, 2.0F, 2.0F);
                    int n = getTextRenderer().getWidth(this.subtitle);
                    this.drawTextBackground(context, getTextRenderer(), 5, n, 16777215 | k);
                    context.drawTextWithShadow(getTextRenderer(), this.subtitle, -n / 2, 5, 16777215 | k);
                    context.getMatrices().pop();
                }

                RenderSystem.depthFunc(GL11.GL_LEQUAL);
                RenderSystem.disableBlend();
                context.getMatrices().pop();
            }

            this.client.getProfiler().pop();
        }
    }

    private int getL(float tickDelta) {
        float h = (float)this.titleRemainTicks - tickDelta;
        int l = 255;
        if (this.titleRemainTicks > this.titleFadeOutTicks + this.titleStayTicks) {
            float o = (float)(this.titleFadeInTicks + this.titleStayTicks + this.titleFadeOutTicks) - h;
            l = (int)(o * 255.0F / (float)this.titleFadeInTicks);
        }

        if (this.titleRemainTicks <= this.titleFadeOutTicks) {
            l = (int)(h * 255.0F / (float)this.titleFadeOutTicks);
        }

        l = MathHelper.clamp(l, 0, 255);
        return l;
    }

}
