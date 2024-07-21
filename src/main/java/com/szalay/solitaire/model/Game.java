package com.szalay.solitaire.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Logger;

public class Game {

    private final static Logger LOGGER = Logger.getLogger("Solitaire");

    private static final int PLAYSTACK2_INITIAL_SIZE = 2;
    private static final int PLAYSTACK3_INITIAL_SIZE = 3;
    private static final int PLAYSTACK4_INITIAL_SIZE = 4;
    private static final int PLAYSTACK5_INITIAL_SIZE = 5;
    private static final int PLAYSTACK6_INITIAL_SIZE = 6;
    private static final int PLAYSTACK7_INITIAL_SIZE = 7;
    private static final int MAX_SELECTION_SIZE = 1;

    private Stack<Card> talon = new Stack<>();
    private final Stack<Card> currentDrawn = new Stack<>();

    private final Stack<Card> targetStack1 = new Stack<>();
    private final Stack<Card> targetStack2 = new Stack<>();
    private final Stack<Card> targetStack3 = new Stack<>();
    private final Stack<Card> targetStack4 = new Stack<>();

    private final Stack<Card> playStack1 = new Stack<>();
    private final Stack<Card> playStack2 = new Stack<>();
    private final Stack<Card> playStack3 = new Stack<>();
    private final Stack<Card> playStack4 = new Stack<>();
    private final Stack<Card> playStack5 = new Stack<>();
    private final Stack<Card> playStack6 = new Stack<>();
    private final Stack<Card> playStack7 = new Stack<>();

    private final Set<Card> allCards = new HashSet<>();

    private final List<GameObserver> observers = new ArrayList<>();

    private boolean started = false;
    private boolean finished = false;

    public void addObserver(final GameObserver observer) {
        this.observers.add(observer);
    }

    private void fireChange() {
        boolean allNotHidden = true;
        for (final Card c : allCards) {
            if (c.isHidden()) {
                allNotHidden = false;
                break;
            }
        }

        if (allNotHidden) {
            finished = true;
        }

        for (final GameObserver o : observers) {
            o.gameChanged();
        }
    }

    public void startGame() {
        allCards.clear();

        if (talon != null) {
            talon.clear();
        }

        currentDrawn.clear();
        playStack1.clear();
        playStack2.clear();
        playStack3.clear();
        playStack4.clear();
        playStack5.clear();
        playStack6.clear();
        playStack7.clear();
        targetStack1.clear();
        targetStack2.clear();
        targetStack3.clear();
        targetStack4.clear();

        final List<Card> cards = new ArrayList<>(Card.createCards());
        Collections.shuffle(cards);

        for (final Card c : cards) {
            allCards.add(c);

            if (playStack1.isEmpty()) {
                playStack1.push(c);
                continue;
            }

            if (playStack2.size() < PLAYSTACK2_INITIAL_SIZE) {
                playStack2.push(c);
                continue;

            }

            if (playStack3.size() < PLAYSTACK3_INITIAL_SIZE) {
                playStack3.push(c);
                continue;
            }

            if (playStack4.size() < PLAYSTACK4_INITIAL_SIZE) {
                playStack4.push(c);
                continue;
            }

            if (playStack5.size() < PLAYSTACK5_INITIAL_SIZE) {
                playStack5.push(c);
                continue;
            }

            if (playStack6.size() < PLAYSTACK6_INITIAL_SIZE) {
                playStack6.push(c);
                continue;
            }

            if (playStack7.size() < PLAYSTACK7_INITIAL_SIZE) {
                playStack7.push(c);
                continue;
            }

            if (talon == null) {
                talon = new Stack<>();
            }

            talon.push(c);
        }

        playStack1.peek().setHidden(false);
        playStack2.peek().setHidden(false);
        playStack3.peek().setHidden(false);
        playStack4.peek().setHidden(false);
        playStack5.peek().setHidden(false);
        playStack6.peek().setHidden(false);
        playStack7.peek().setHidden(false);

        started = true;
        fireChange();
    }

    public Stack<Card> getTalon() {
        return talon;
    }

    public Stack<Card> getTargetStack1() {
        return targetStack1;
    }

    public Stack<Card> getTargetStack2() {
        return targetStack2;
    }

    public Stack<Card> getTargetStack3() {
        return targetStack3;
    }

    public Stack<Card> getTargetStack4() {
        return targetStack4;
    }

    public Stack<Card> getPlayStack1() {
        return playStack1;
    }

    public Stack<Card> getPlayStack2() {
        return playStack2;
    }

    public Stack<Card> getPlayStack3() {
        return playStack3;
    }

    public Stack<Card> getPlayStack4() {
        return playStack4;
    }

    public Stack<Card> getPlayStack5() {
        return playStack5;
    }

    public Stack<Card> getPlayStack6() {
        return playStack6;
    }

    public Stack<Card> getPlayStack7() {
        return playStack7;
    }

    public boolean isStarted() {
        return started;
    }

    public void cardClicked(Card card) {
        LOGGER.info("Card clicked: " + card);

        final Stack<Card> sourceStack = getSourceStack(card);
        boolean done = putCardIfPossible(card, targetStack1);
        if (done) {
            handleSourceStack(sourceStack);
            return;
        }

        done = putCardIfPossible(card, targetStack2);
        if (done) {
            handleSourceStack(sourceStack);
            return;
        }

        done = putCardIfPossible(card, targetStack3);
        if (done) {
            handleSourceStack(sourceStack);
            return;
        }

        done = putCardIfPossible(card, targetStack4);
        if (done) {
            handleSourceStack(sourceStack);
            return;
        }

        done = putCardIfPossible(card, playStack1);
        if (done) {
            handleSourceStack(sourceStack);
            return;
        }

        done = putCardIfPossible(card, playStack2);
        if (done) {
            handleSourceStack(sourceStack);
            return;
        }

        done = putCardIfPossible(card, playStack3);
        if (done) {
            handleSourceStack(sourceStack);
            return;
        }

        done = putCardIfPossible(card, playStack4);
        if (done) {
            handleSourceStack(sourceStack);
            return;
        }

        done = putCardIfPossible(card, playStack5);
        if (done) {
            handleSourceStack(sourceStack);
            return;
        }

        done = putCardIfPossible(card, playStack6);
        if (done) {
            handleSourceStack(sourceStack);
            return;
        }

        done = putCardIfPossible(card, playStack7);
        if (done) {
            handleSourceStack(sourceStack);
        }
    }

    private void handleSourceStack(Stack<Card> sourceStack) {
        sourceStack.pop();

        if (!sourceStack.isEmpty()) {
            sourceStack.peek().setHidden(false);
        }

        fireChange();
    }

    private Stack<Card> getSourceStack(Card card) {
        if (currentDrawn.contains(card)) {
            return currentDrawn;
        }

        if (targetStack1.contains(card)) {
            return targetStack1;
        }
        if (targetStack2.contains(card)) {
            return targetStack2;
        }
        if (targetStack3.contains(card)) {
            return targetStack3;
        }
        if (targetStack4.contains(card)) {
            return targetStack4;
        }

        if (playStack1.contains(card)) {
            return playStack1;
        }
        if (playStack2.contains(card)) {
            return playStack2;
        }
        if (playStack3.contains(card)) {
            return playStack3;
        }
        if (playStack4.contains(card)) {
            return playStack4;
        }
        if (playStack5.contains(card)) {
            return playStack5;
        }
        if (playStack6.contains(card)) {
            return playStack6;
        }
        if (playStack7.contains(card)) {
            return playStack7;
        }

        return talon;
    }

    private boolean putCardIfPossible(Card card, Stack<Card> targetStack) {
        if (isInStack(targetStack, card)) {
            return false;
        }

        return handleIfPossible(targetStack, card);
    }

    private boolean isInStack(Stack<Card> checkStack, Card card) {
        for (final Card c : checkStack) {
            if (c.equals(card)) {
                return true;
            }
        }

        return false;
    }

    private boolean handleIfPossible(Stack<Card> stack, Card card) {
        if (stack.isEmpty()) {
            if (isATargetStack(stack)) {
                if (card.getNumber() == CardNumber.NR_AS) {
                    stack.push(card);
                    return true;
                }
            }

            if (isAPlaystack(stack)) {
                if (card.getNumber() == CardNumber.NR_KING) {
                    stack.push(card);
                    return true;
                }
            }

            return false;
        }

        final Card cardFromStack = stack.peek();
        if (card.canBeDroppedOn(cardFromStack, isATargetStack(stack))) {
            stack.push(card);
            cardFromStack.setHidden(false);
            return true;
        }
        return false;
    }

    private boolean isATargetStack(Stack<Card> stack) {
        return stack == targetStack1 || stack == targetStack2 || stack == targetStack3 || stack == targetStack4;
    }

    private boolean isAPlaystack(Stack<Card> stack) {
        return stack == playStack1 || stack == playStack2 || stack == playStack3 || stack == playStack4 ||
                stack == playStack5 || stack == playStack6 || stack == playStack7;
    }


    public void cardDroppedOn(Card sourceCard, Card targetCard) {
        LOGGER.info("Card dropped on: " + sourceCard + ", " + targetCard);

        final Stack<Card> targetStack = getSourceStack(targetCard);
        final Stack<Card> sourceStack = getSourceStack(sourceCard);

        final boolean done = putCardIfPossible(sourceCard, targetStack);
        if (done) {
            handleSourceStack(sourceStack);
        }
    }

    public Card getCardFromString(String cardAsString) {
        for (final Card c : allCards) {
            if (c.toString().equals(cardAsString)) {
                return c;
            }
        }

        return null;
    }

    public void cardDropOnTargetStack1(Card sourceCard) {
        LOGGER.info("Card dropped on target stack 1: " + sourceCard);
    }

    public void cardDropOnTargetStack2(Card sourceCard) {
        LOGGER.info("Card dropped on target stack 2: " + sourceCard);

    }

    public void cardDropOnTargetStack3(Card sourceCard) {
        LOGGER.info("Card dropped on target stack 3: " + sourceCard);

    }

    public void cardDropOnTargetStack4(Card sourceCard) {
        LOGGER.info("Card dropped on target stack 4: " + sourceCard);
    }

    public void talonClicked() {
        LOGGER.info("Talon clicked");

        if (talon.isEmpty()) {
            return;
        }

        if (talon.size() >= MAX_SELECTION_SIZE) {
            if (!currentDrawn.isEmpty()) {
                currentDrawn.pop();
            }
        }

        final Card fromTalon = talon.pop();
        fromTalon.setHidden(false);
        currentDrawn.push(fromTalon);

        Stack<Card> newTalon = new Stack<>();
        newTalon.push(fromTalon);
        for (final Card cardFromTalon : talon) {
            newTalon.push(cardFromTalon);
        }

        talon = newTalon;
        fireChange();
    }

    public Stack<Card> getCurrentDrawn() {
        return currentDrawn;
    }

    public boolean isFinished() {
        return finished;
    }
}
