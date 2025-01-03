//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hell0hd.orangechin.entity.client;

import com.google.common.collect.ImmutableList;
import hell0hd.orangechin.Orangechin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class CarmapoModel<T extends Entity> extends SmallBipedalModel<T> {
    public static final EntityModelLayer CARMAPO = new EntityModelLayer(Identifier.of(Orangechin.MOD_ID, "carmapo"), "main");
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    public CarmapoModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftLeg = root.getChild("leftLeg");
        this.rightLeg = root.getChild("rightLeg");
        this.rightArm = root.getChild("rightArm");
        this.leftArm = root.getChild("leftArm");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(1.0F)), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(8, 8).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

        ModelPartData leftLeg = modelPartData.addChild("leftLeg", ModelPartBuilder.create().uv(0, 8).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(1.0F, 18.0F, 0.0F));

        ModelPartData rightLeg = modelPartData.addChild("rightLeg", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 18.0F, 0.0F));

        ModelPartData rightArm = modelPartData.addChild("rightArm", ModelPartBuilder.create().uv(20, 8).cuboid(-1.5F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, 13.0F, 0.0F));

        ModelPartData leftArm = modelPartData.addChild("leftArm", ModelPartBuilder.create().uv(20, 8).cuboid(-0.25F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.25F, 14.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 16);
    }

    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.rightLeg, this.leftLeg, this.rightArm, this.leftArm);
    }

    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * ((float)Math.PI / 180F);
        this.head.yaw = headYaw * ((float)Math.PI / 180F);
        this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
    }
}
