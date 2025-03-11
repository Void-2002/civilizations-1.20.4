package net._void.civilizations.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum NordicCivilianVariant {
    CIVILIAN1(1),
    CIVILIAN2(2),
    CIVILIAN3(3),
    CIVILIAN4(4);

    private static final NordicCivilianVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(NordicCivilianVariant::getId)).toArray(NordicCivilianVariant[]::new);
    private final int id;

    NordicCivilianVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static NordicCivilianVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
