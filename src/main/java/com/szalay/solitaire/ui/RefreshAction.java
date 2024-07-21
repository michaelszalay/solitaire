package com.szalay.solitaire.ui;

import com.szalay.solitaire.util.TextUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

final class RefreshAction implements EventHandler<ActionEvent> {

    private final GameUI game;

    RefreshAction(GameUI game) {
        this.game = game;
    }

    @Override
    public void handle(ActionEvent event) {
        if (!game.getGame().isStarted()) {
            game.getGame().startGame();
            return;
        }

        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(TextUtil.getText("questionTitle"));
        alert.setContentText(TextUtil.getText("reallyStartNewQuestion"));

        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            game.getGame().startGame();
        }
    }
}
