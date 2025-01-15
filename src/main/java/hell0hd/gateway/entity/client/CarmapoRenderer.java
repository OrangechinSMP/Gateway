package hell0hd.gateway.entity.client;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.entity.custom.CarmapoEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class CarmapoRenderer extends MobEntityRenderer<CarmapoEntity, CarmapoModel<CarmapoEntity>> {
    private static final Identifier ANGRY = Identifier.of(Gateway.MOD_ID, "textures/entity/carmapo/carmapo_angry.png");
    private static final Identifier GODIS = Identifier.of(Gateway.MOD_ID, "textures/entity/carmapo/carmapo_godis.png");
    public CarmapoRenderer(EntityRendererFactory.Context context) {
        super(context, new CarmapoModel<>(context.getPart(CarmapoModel.CARMAPO)), 0.3f);
    }

    @Override
    public Identifier getTexture(CarmapoEntity entity) {
        String string = Formatting.strip(entity.getName().getString());
        if (entity.hasAngerTime()) {
            return ANGRY;
        } else {
            if ("Godis".equals(string)) {
                return GODIS;
            } else {
                return  Identifier.of(Gateway.MOD_ID, "textures/entity/carmapo/carmapo.png");
            }
        }
    }
}
