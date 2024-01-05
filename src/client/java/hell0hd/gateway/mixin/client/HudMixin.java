package hell0hd.gateway.mixin.client;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class HudMixin {
	@Shadow @Final private MinecraftClient client;

	@Inject(
			at = @At(
					value = "HEAD"
			),
			method = "onKey",
			cancellable = true
	)
	private void gateway$handleHud(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
		if (key == 290) {
			if (((TitleMixin) client.inGameHud).getTitle() != null) {
				ci.cancel();
			}
		}
	}
}