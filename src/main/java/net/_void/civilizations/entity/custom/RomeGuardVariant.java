package net._void.civilizations.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum RomeGuardVariant {
    CIVILIAN1(1),
    CIVILIAN2(2);

    private static final RomeGuardVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(RomeGuardVariant::getId)).toArray(RomeGuardVariant[]::new);
    private final int id;

    RomeGuardVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static RomeGuardVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
