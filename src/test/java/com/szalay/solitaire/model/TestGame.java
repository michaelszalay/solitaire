package com.szalay.solitaire.model;

import org.junit.Assert;
import org.junit.Test;

public class TestGame {

    @Test
    public void testInitGame() {
        final Game game = new Game();
        game.startGame();

        Assert.assertEquals(24, game.getTalon().size());
        Assert.assertEquals(1, game.getPlayStack1().size());
        Assert.assertEquals(2, game.getPlayStack2().size());
        Assert.assertEquals(3, game.getPlayStack3().size());
        Assert.assertEquals(4, game.getPlayStack4().size());
        Assert.assertEquals(5, game.getPlayStack5().size());
        Assert.assertEquals(6, game.getPlayStack6().size());
        Assert.assertEquals(7, game.getPlayStack7().size());

        Assert.assertEquals(0, game.getTargetStack1().size());
        Assert.assertEquals(0, game.getTargetStack2().size());
        Assert.assertEquals(0, game.getTargetStack3().size());
        Assert.assertEquals(0, game.getTargetStack4().size());
    }
}
