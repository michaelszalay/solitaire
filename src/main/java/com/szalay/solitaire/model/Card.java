package com.szalay.solitaire.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Card {

    private final CardColor color;
    private final CardNumber number;
    private final CardType type;
    private boolean hidden;

    Card(CardNumber number, CardType type) {
        this.color = type.getColor();
        this.number = number;
        this.type = type;
        this.hidden = true;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public CardType getType() {
        return type;
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardColor getColor() {
        return color;
    }

    static List<Card> createCards() {
        final List<Card> cards = new ArrayList<>(52);
        for (final CardType type : CardType.values()) {
            for (final CardNumber number : CardNumber.values()) {
                cards.add(new Card(number, type));
            }
        }

        return Collections.unmodifiableList(cards);
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", number=" + number +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return color == card.color && number == card.number && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, number, type);
    }

    public boolean canBeDroppedOn(Card cardFromStack, boolean inTargetStack) {
        if (inTargetStack) {
            if (color != cardFromStack.getColor()) {
                return false;
            }

            if (type != cardFromStack.getType()) {
                return false;
            }

            return number.ordinal() == (cardFromStack.number.ordinal() + 1);
        }
        else {
            if (color == cardFromStack.getColor()) {
                return false;
            }
        }

        return cardFromStack.getNumber().ordinal() == (number.ordinal() + 1);
    }
}
