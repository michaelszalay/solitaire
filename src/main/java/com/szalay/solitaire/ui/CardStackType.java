package com.szalay.solitaire.ui;

public enum CardStackType {

    TALON,
    SELECTION,
    TARGET_1,
    TARGET_2,
    TARGET_3,
    TARGET_4,
    PLAYFIELD;

    boolean isTarget() {
        return this == TARGET_1 || this == TARGET_2 || this == TARGET_3 || this == TARGET_4;
    }
}
