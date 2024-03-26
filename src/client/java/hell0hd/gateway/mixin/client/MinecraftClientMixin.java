package hell0hd.gateway.mixin.client;

import hell0hd.gateway.GatewayClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

   // @Inject(method = "openChatScreen", at = @At("HEAD"), cancellable = true)
    protected void stopChat(String text, CallbackInfo ci) {
        if (!GatewayClient.allow_chat_usage) {
            ci.cancel();
        }
    }
}