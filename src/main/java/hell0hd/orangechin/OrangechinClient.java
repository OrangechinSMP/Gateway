package hell0hd.orangechin;

import hell0hd.orangechin.entity.ModEntities;
import hell0hd.orangechin.entity.client.CarmapoModel;
import hell0hd.orangechin.entity.client.CarmapoRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;

public class OrangechinClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(CarmapoModel.CARMAPO, CarmapoModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CARMAPO, CarmapoRenderer::new);
    }
}
