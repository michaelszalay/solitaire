package com.szalay.solitaire;

import com.szalay.solitaire.model.Game;
import com.szalay.solitaire.ui.GameUI;
import com.szalay.solitaire.util.TextUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.logging.Logger;

public class EntryPoint extends Application {

    private final static Logger LOGGER = Logger.getLogger("Solitaire");

    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("Starting solitaire...");

        primaryStage.setTitle(TextUtil.getText("mainWindowTitle"));
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/solitaire.png"))));

        final VBox root = new VBox();

        final Game game = new Game();
        final GameUI ui = new GameUI(root, game);
        game.addObserver(ui);

        primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.show();
    }
}
