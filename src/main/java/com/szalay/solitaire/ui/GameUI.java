package com.szalay.solitaire.ui;

import com.szalay.solitaire.model.Game;
import com.szalay.solitaire.model.GameObserver;
import com.szalay.solitaire.util.TextUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameUI implements GameObserver {

    private final VBox content;
    private final Game game;

    public GameUI(VBox root, Game game) {
        this.game = game;

        root.getChildren().add(ToolbarFactory.createToolbar(this));
        this.content = new VBox();
        root.getChildren().add(content);

        gameChanged();
    }

    @Override
    public void gameChanged() {
        content.getChildren().clear();

        if (!game.isStarted()) {
            content.getChildren().add(new Label(TextUtil.getText("gameNotStarted")));
        } else {

            final GridPane grid = new GridPane();
            grid.add(new CardStack(game.getTalon(), game, CardStackType.TALON), 0, 0);

            grid.add(new CardStack(game.getCurrentDrawn(), game, CardStackType.SELECTION), 1, 0);

            grid.add(new CardStack(game.getTargetStack1(), game, CardStackType.TARGET_1), 4, 0);
            grid.add(new CardStack(game.getTargetStack2(), game, CardStackType.TARGET_2), 5, 0);
            grid.add(new CardStack(game.getTargetStack3(), game, CardStackType.TARGET_3), 6, 0);
            grid.add(new CardStack(game.getTargetStack4(), game, CardStackType.TARGET_4), 7, 0);

            grid.add(new CardStack(game.getPlayStack1(), game, CardStackType.PLAYFIELD), 1, 1);
            grid.add(new CardStack(game.getPlayStack2(), game, CardStackType.PLAYFIELD), 2, 1);
            grid.add(new CardStack(game.getPlayStack3(), game, CardStackType.PLAYFIELD), 3, 1);
            grid.add(new CardStack(game.getPlayStack4(), game, CardStackType.PLAYFIELD), 4, 1);
            grid.add(new CardStack(game.getPlayStack5(), game, CardStackType.PLAYFIELD), 5, 1);
            grid.add(new CardStack(game.getPlayStack6(), game, CardStackType.PLAYFIELD), 6, 1);
            grid.add(new CardStack(game.getPlayStack7(), game, CardStackType.PLAYFIELD), 7, 1);

            content.getChildren().add(grid);
        }

        if (game.isFinished()) {
            final Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(TextUtil.getText("finishedTitle"));
            alert.setContentText(TextUtil.getText("youWon"));
            alert.showAndWait();
        }
    }


    Game getGame() {
        return game;
    }
}
