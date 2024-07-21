package com.szalay.solitaire.model;

public enum CardType {

    KREUZ(CardColor.RED),
    PIQUE(CardColor.BLACK),
    HERZ(CardColor.RED),
    KARO(CardColor.BLACK);

    private final CardColor color;

    CardType(CardColor color) {
        this.color = color;
    }

    public CardColor getColor() {
        return color;
    }
}
