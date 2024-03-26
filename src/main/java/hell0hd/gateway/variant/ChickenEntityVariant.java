package hell0hd.gateway.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum ChickenEntityVariant {
    CHANTECLER_CHICKEN(0),
    WELSUMMER_CHICKEN(1),
    SPECKLEDY_CHICKEN(2),
    BLACK_WOLF(3),
    CHESTNUT_WOLF(4),
    RUSTY_WOLF(5),
    SPOTTED_WOLF(6),
    STRIPED_WOLF(7),
    SNOWY_WOLF(8);

    private static final ChickenEntityVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(ChickenEntityVariant::getId)).toArray(ChickenEntityVariant[]::new);
    private final int id;

    ChickenEntityVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ChickenEntityVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }}