package hell0hd.gateway.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent PAUX = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 0), 0.3F)
            .build();
}
