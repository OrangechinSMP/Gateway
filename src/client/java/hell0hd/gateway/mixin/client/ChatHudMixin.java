package hell0hd.gateway.mixin.client;

import hell0hd.gateway.GatewayClient;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    @Inject(method = "isChatHidden", at = @At("RETURN"), cancellable = true)
    protected void stopRender(CallbackInfoReturnable<Boolean> cir) {
        if (!GatewayClient.render_chat) {
            cir.setReturnValue(true);
        }
    }
}