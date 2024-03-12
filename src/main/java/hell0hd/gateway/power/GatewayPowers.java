package hell0hd.gateway.power;

import hell0hd.gateway.Gateway;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;

public class GatewayPowers {
    public static final PowerType<Power> QUICK_BLOOD = new PowerTypeReference<>(Gateway.id("quick_blood"));
}
