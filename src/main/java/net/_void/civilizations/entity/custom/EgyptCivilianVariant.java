package net._void.civilizations.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum EgyptCivilianVariant {
    CIVILIAN1(1),
    CIVILIAN2(2),
    CIVILIAN3(3),
    CIVILIAN4(4);

    private static final EgyptCivilianVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(EgyptCivilianVariant::getId)).toArray(EgyptCivilianVariant[]::new);
    private final int id;

    EgyptCivilianVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static EgyptCivilianVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
