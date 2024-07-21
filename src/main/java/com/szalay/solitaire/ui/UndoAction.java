package com.szalay.solitaire.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

final class UndoAction implements EventHandler<ActionEvent> {

    private final GameUI game;

    UndoAction(GameUI game) {
        this.game = game;
    }

    @Override
    public void handle(ActionEvent event) {
        game.getGame().restoreLatestState();
    }
}
