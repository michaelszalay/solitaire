package com.szalay.solitaire.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestCard {

    @Test
    public void testCreateCards() {
        final List<Card> cards = Card.createCards();

        Assert.assertEquals(52, cards.size());
    }

    @Test
    public void canBeDroppedOnInPlaystack() {
        final Card ten = new Card(CardNumber.NR_10, CardType.KARO);
        final Card nine = new Card(CardNumber.NR_9, CardType.HERZ);

        Assert.assertTrue(nine.canBeDroppedOn(ten, false));
    }
}