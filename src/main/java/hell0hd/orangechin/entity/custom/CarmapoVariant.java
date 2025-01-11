package hell0hd.orangechin.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum CarmapoVariant {
    DEFAULT(0),
    GUNCHEE(1),
    NEUTRAL(2),
    COLONTHREE(3);

    private static final CarmapoVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(CarmapoVariant::getId)).toArray(CarmapoVariant[]::new);
    private final int id;

    CarmapoVariant(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public static CarmapoVariant byId(int id){
        return BY_ID[id % BY_ID.length];
    }

    
}
