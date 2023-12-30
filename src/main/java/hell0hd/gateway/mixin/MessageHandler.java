package hell0hd.gateway.mixin;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class MessageHandler {

    @Inject(at = @At("HEAD"), method = "onChatMessage", cancellable = true)
    public void gateway$message(ChatMessageC2SPacket packet, CallbackInfo ci) {
        ci.cancel();
    }

}
