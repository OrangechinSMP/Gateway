package hell0hd.orangechin.entity.client;

import hell0hd.orangechin.Orangechin;
import hell0hd.orangechin.entity.custom.CarmapoEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CarmapoRenderer extends MobEntityRenderer<CarmapoEntity, CarmapoModel<CarmapoEntity>> {
    public CarmapoRenderer(EntityRendererFactory.Context context) {
        super(context, new CarmapoModel<>(context.getPart(CarmapoModel.CARMAPO)), 0.3f);
    }

    @Override
    public Identifier getTexture(CarmapoEntity entity) {
        return Identifier.of(Orangechin.MOD_ID, "textures/entity/carmapo/carmapo.png");
    }
}
