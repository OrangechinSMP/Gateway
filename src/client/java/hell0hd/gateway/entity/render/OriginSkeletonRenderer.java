package hell0hd.gateway.entity.render;

import hell0hd.gateway.Gateway;
import hell0hd.gateway.entity.custom.skeleton.OriginSkeletonEntity;
import hell0hd.gateway.entity.model.OriginSkeletonModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.util.Identifier;

@Environment(value=EnvType.CLIENT)
public class OriginSkeletonRenderer extends BipedEntityRenderer<OriginSkeletonEntity, OriginSkeletonModel<OriginSkeletonEntity>> {
    private static final Identifier TEXTURE = new Identifier(Gateway.MOD_ID, "textures/entity/origins/undead/skeleton/skeleton.png");

    public OriginSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public OriginSkeletonRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new OriginSkeletonModel(ctx.getPart(layer)), 0.5f);
        this.addFeature(new ArmorFeatureRenderer(this, new OriginSkeletonModel(ctx.getPart(legArmorLayer)), new OriginSkeletonModel(ctx.getPart(bodyArmorLayer)), ctx.getModelManager()));
    }

    @Override
    public Identifier getTexture(OriginSkeletonEntity originSkeletonEntity) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(OriginSkeletonEntity originSkeletonEntity) {
        return originSkeletonEntity.isShaking();
    }

}