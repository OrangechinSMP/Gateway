package hell0hd.orangechin.entity.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public abstract class SmallBipedalModel<E extends Entity> extends EntityModel<E> {
    private final boolean headScaled;
    private final float childHeadYOffset;
    private final float childHeadZOffset;
    private final float invertedChildHeadScale;
    private final float invertedChildBodyScale;
    private final float childBodyYOffset;

    protected SmallBipedalModel(boolean headScaled, float childHeadYOffset, float childHeadZOffset) {
        this(headScaled, childHeadYOffset, childHeadZOffset, 2.0F, 2.0F, 24.0F);
    }

    protected SmallBipedalModel(boolean headScaled, float childHeadYOffset, float childHeadZOffset, float invertedChildHeadScale, float invertedChildBodyScale, float childBodyYOffset) {
        this(RenderLayer::getEntityCutoutNoCull, headScaled, childHeadYOffset, childHeadZOffset, invertedChildHeadScale, invertedChildBodyScale, childBodyYOffset);
    }

    protected SmallBipedalModel(Function<Identifier, RenderLayer> renderLayerFactory, boolean headScaled, float childHeadYOffset, float childHeadZOffset, float invertedChildHeadScale, float invertedChildBodyScale, float childBodyYOffset) {
        super(renderLayerFactory);
        this.headScaled = headScaled;
        this.childHeadYOffset = childHeadYOffset;
        this.childHeadZOffset = childHeadZOffset;
        this.invertedChildHeadScale = invertedChildHeadScale;
        this.invertedChildBodyScale = invertedChildBodyScale;
        this.childBodyYOffset = childBodyYOffset;
    }

    protected SmallBipedalModel() {
        this(false, 6.0F, 0.0F);
    }

    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        if (this.child) {
            matrices.push();
            if (this.headScaled) {
                float f = 1.5F / this.invertedChildHeadScale;
                matrices.scale(f, f, f);
            }

            matrices.translate(0.0F, this.childHeadYOffset / 16.0F, this.childHeadZOffset / 16.0F);
            this.getHeadParts().forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay, color));
            matrices.pop();
            matrices.push();
            float f = 1.0F / this.invertedChildBodyScale;
            matrices.scale(f, f, f);
            matrices.translate(0.0F, this.childBodyYOffset / 16.0F, 0.0F);
            this.getBodyParts().forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay, color));
            matrices.pop();
        } else {
            this.getHeadParts().forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay, color));
            this.getBodyParts().forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay, color));
        }

    }

    protected abstract Iterable<ModelPart> getHeadParts();

    protected abstract Iterable<ModelPart> getBodyParts();
}