package com.szalay.solitaire.ui;

import com.szalay.solitaire.model.Card;
import com.szalay.solitaire.model.Game;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

final class CardComponent extends VBox {

    CardComponent(Card card, Game game, boolean isCurrentSelected, boolean isLast) {
        if (card == null) {
            return;
        }

        final String imageResource = getImage(card);
        Image image = new Image(Objects.requireNonNull(CardComponent.class.getResourceAsStream("/cards/" + imageResource + ".gif")));
        if (!isLast) {
            image = cropImage(image);
        }

        final ImageView cardComponent = new ImageView(image);
        cardComponent.setOnMouseClicked(event -> {
            game.cardClicked(card);
        });

        cardComponent.setOnDragDetected(event -> {

            final Dragboard db = cardComponent.startDragAndDrop(TransferMode.ANY);

            final Map<DataFormat, Object> data = new HashMap<>();
            data.put(DataFormat.PLAIN_TEXT, card.toString());
            db.setContent(data);

            event.consume();
        });

        if (!isCurrentSelected) {
            cardComponent.setOnDragOver(event -> {
                if (event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            });


            cardComponent.setOnDragDropped(event -> {
                final Dragboard dragboard = event.getDragboard();
                if (!dragboard.hasString()) {
                    return;
                }

                final String cardAsString = (String) dragboard.getContent(DataFormat.PLAIN_TEXT);
                final Card sourceCard = game.getCardFromString(cardAsString);
                if (sourceCard == null) {
                    return;
                }

                game.cardDroppedOn(sourceCard, card);
                event.consume();
            });
        }

        getChildren().add(cardComponent);
    }

    private Image cropImage(Image image) {
        final PixelReader reader = image.getPixelReader();
        return new WritableImage(reader, 0, 0, (int) image.getWidth(), 20);
    }

    private String getImage(final Card card) {
        String firstString = "";

        switch (card.getNumber()) {
            case NR_AS -> {
                firstString = "ace";
            }
            case NR_2 -> {
                firstString = "two";
            }
            case NR_3 -> {
                firstString = "three";
            }
            case NR_4 -> {
                firstString = "four";
            }
            case NR_5 -> {
                firstString = "five";
            }
            case NR_6 -> {
                firstString = "six";
            }
            case NR_7 -> {
                firstString = "seven";
            }
            case NR_8 -> {
                firstString = "eight";
            }
            case NR_9 -> {
                firstString = "nine";
            }
            case NR_10 -> {
                firstString = "ten";
            }
            case NR_JUNGE -> {
                firstString = "jack";
            }
            case NR_QUEEN -> {
                firstString = "queen";
            }
            case NR_KING -> {
                firstString = "king";
            }

        }

        String secondString = "";
        switch (card.getType()) {
            case KREUZ -> {
                secondString = "diamonds";
            }
            case PIQUE -> {
                secondString = "spades";
            }
            case HERZ -> {
                secondString = "hearts";
            }
            case KARO -> {
                secondString = "clubs";
            }
        }

        return firstString + "-of-" + secondString;
    }
}
