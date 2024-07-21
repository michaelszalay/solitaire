package com.szalay.solitaire.ui;

import com.szalay.solitaire.util.TextUtil;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

final class ToolbarFactory {

    static VBox createToolbar(final GameUI gameUI) {

        final ToolBar toolBar = new ToolBar();

        final Button refreshButton = new Button();
        refreshButton.setGraphic(createImage("refresh.png"));
        refreshButton.setTooltip(new Tooltip(TextUtil.getText("refreshButton")));
        refreshButton.setOnAction(new RefreshAction(gameUI));
        toolBar.getItems().add(refreshButton);

        return new VBox(toolBar);
    }

    private static ImageView createImage(final String name) {
        return new ImageView(new Image(Objects.requireNonNull(GameUI.class.getResourceAsStream("/images/" + name))));
    }

}
