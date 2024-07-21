package com.szalay.solitaire.ui;

import java.util.Objects;
import java.util.Stack;
import com.szalay.solitaire.model.Card;
import com.szalay.solitaire.model.Game;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

final class CardStack extends VBox {

    CardStack(Stack<Card> cards, Game game, int targetStackNumber, boolean isTalon) {
        super();

        if (isTalon) {
            final Image talonImage = new Image(Objects.requireNonNull(CardComponent.class.getResourceAsStream("/cards/back.gif")));
            final ImageView talonImageComponent = new ImageView(talonImage);
            talonImageComponent.setOnMouseClicked(event -> game.talonClicked());
            getChildren().add(talonImageComponent);
            return;
        }

        if (cards.isEmpty()) {
            if (targetStackNumber == -1) {
                return;
            }

            final Image image = new Image(Objects.requireNonNull(CardComponent.class.getResourceAsStream("/cards/back.gif")));
            final ImageView emptyStackComponent = new ImageView(image);
            emptyStackComponent.setOnDragOver(event -> {
                if (event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            });

            emptyStackComponent.setOnDragDropped(event -> {
                final Dragboard dragboard = event.getDragboard();
                if (!dragboard.hasString()) {
                    return;
                }

                final String cardAsString = (String) dragboard.getContent(DataFormat.PLAIN_TEXT);
                final Card sourceCard = game.getCardFromString(cardAsString);
                if (sourceCard == null) {
                    return;
                }

                if (targetStackNumber == 1) {
                    game.cardDropOnTargetStack1(sourceCard);
                }
                if (targetStackNumber == 2) {
                    game.cardDropOnTargetStack2(sourceCard);
                }
                if (targetStackNumber == 3) {
                    game.cardDropOnTargetStack3(sourceCard);
                }
                if (targetStackNumber == 4) {
                    game.cardDropOnTargetStack4(sourceCard);
                }

                event.consume();
            });

            getChildren().add(emptyStackComponent);
            return;
        }

        final Card last = cards.peek();
        for (final Card card : cards) {
            final boolean isLast = card == last;
            if (!card.isHidden()) {
                getChildren().add(new CardComponent(card, game, targetStackNumber == -1, isLast));
            }
            else {
                final Image image = new Image(Objects.requireNonNull(CardComponent.class.getResourceAsStream("/cards/hidden.gif")));
                final ImageView hiddenImage = new ImageView(image);
                getChildren().add(hiddenImage);
            }
        }
    }
}
