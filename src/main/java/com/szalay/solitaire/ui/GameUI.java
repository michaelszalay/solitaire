package com.szalay.solitaire.ui;

import com.szalay.solitaire.model.Game;
import com.szalay.solitaire.model.GameObserver;
import com.szalay.solitaire.util.TextUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameUI implements GameObserver {

    private final VBox root;
    private final VBox content;
    private final Game game;

    public GameUI(VBox root, Game game) {
        this.root = root;
        this.game = game;

        this.root.getChildren().add(ToolbarFactory.createToolbar(this));
        this.content = new VBox();
        this.root.getChildren().add(content);

        gameChanged();
    }

    @Override
    public void gameChanged() {
        content.getChildren().clear();

        if (!game.isStarted()) {
            content.getChildren().add(new Label(TextUtil.getText("gameNotStarted")));
        } else {

            final GridPane grid = new GridPane();
            grid.add(new CardStack(game.getTalon(), game, 0, true), 0, 0);

            grid.add(new CardStack(game.getCurrentDrawn(), game, -1, false), 1, 0);

            grid.add(new CardStack(game.getTargetStack1(), game, 1, false), 4, 0);
            grid.add(new CardStack(game.getTargetStack2(), game, 2, false), 5, 0);
            grid.add(new CardStack(game.getTargetStack3(), game, 3, false), 6, 0);
            grid.add(new CardStack(game.getTargetStack4(), game, 4, false), 7, 0);

            grid.add(new CardStack(game.getPlayStack1(), game, 0, false), 1, 1);
            grid.add(new CardStack(game.getPlayStack2(), game, 0, false), 2, 1);
            grid.add(new CardStack(game.getPlayStack3(), game, 0, false), 3, 1);
            grid.add(new CardStack(game.getPlayStack4(), game, 0, false), 4, 1);
            grid.add(new CardStack(game.getPlayStack5(), game, 0, false), 5, 1);
            grid.add(new CardStack(game.getPlayStack6(), game, 0, false), 6, 1);
            grid.add(new CardStack(game.getPlayStack7(), game, 0, false), 7, 1);

            content.getChildren().add(grid);
        }

        if (game.isFinished()) {
            final Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(TextUtil.getText("finishedTitle"));
            alert.setContentText(TextUtil.getText("youWon"));
        }
    }


    Game getGame() {
        return game;
    }
}
