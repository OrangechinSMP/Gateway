package hell0hd.orangechin.entity.client;

import com.google.common.collect.Maps;
import hell0hd.orangechin.Orangechin;
import hell0hd.orangechin.entity.custom.CarmapoEntity;
import hell0hd.orangechin.entity.custom.CarmapoVariant;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class CarmapoRenderer extends MobEntityRenderer<CarmapoEntity, CarmapoModel<CarmapoEntity>> {
    private static final Identifier ANGRY = Identifier.of(Orangechin.MOD_ID, "textures/entity/carmapo/carmapo_angry.png");
    private static final Identifier GODIS = Identifier.of(Orangechin.MOD_ID, "textures/entity/carmapo/carmapo_godis.png");
    private static final Map<CarmapoVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(CarmapoVariant.class), map -> {
                map.put(CarmapoVariant.DEFAULT,
                        Identifier.of(Orangechin.MOD_ID, "textures/entity/carmapo/carmapo.png"));
                map.put(CarmapoVariant.GUNCHEE,
                        Identifier.of(Orangechin.MOD_ID, "textures/entity/carmapo/carmapo_gunchee.png"));
                map.put(CarmapoVariant.NEUTRAL,
                        Identifier.of(Orangechin.MOD_ID, "textures/entity/carmapo/carmapo_neutral.png"));
                map.put(CarmapoVariant.COLONTHREE,
                        Identifier.of(Orangechin.MOD_ID, "textures/entity/carmapo/carmapo_colonthree.png"));
            });
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
                return Identifier.of(Orangechin.MOD_ID, "textures/entity/carmapo/carmapo_godis.png");
            } else {
                return LOCATION_BY_VARIANT.get(entity.getVariant());
            }
        }
    }
}
