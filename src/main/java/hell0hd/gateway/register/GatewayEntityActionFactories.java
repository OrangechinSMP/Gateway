package hell0hd.gateway.register;

import hell0hd.gateway.action.entity.RandomTeleportAction;
import hell0hd.gateway.action.entity.SummonSkeletonAction;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;

public class GatewayEntityActionFactories  {

    public static void register() {
        register(SummonSkeletonAction.createFactory());
        register(RandomTeleportAction.getFactory());

    }

    private static void register(ActionFactory<Entity> serializer) {
        Registry.register(ApoliRegistries.ENTITY_ACTION, serializer.getSerializerId(), serializer);
    }
}
