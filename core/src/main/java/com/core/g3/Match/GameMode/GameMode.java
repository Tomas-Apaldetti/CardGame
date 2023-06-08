package com.core.g3.Match.GameMode;

import com.core.g3.Card.CardName;
import com.core.g3.Deck.IDeck;
import com.core.g3.Match.GameMode.Exceptions.InvalidDeckCount;
import com.core.g3.Match.Player.Player;
import com.core.g3.User.User;

import java.util.HashMap;

public abstract class GameMode {
    protected int combatZoneLimit;
    protected int artifactZoneLimit;
    protected int reserveZoneLimit;
    protected int initialHandSize;
    protected int MAXIMUM_CARDS_PER_DECK;
    protected int MINIMUM_CARDS_PER_DECK;
    protected int MAXIMUM_REPEATED_CARDS;
    protected int initialPoints;

    public int getCombatZoneLimit() {
        return this.combatZoneLimit;
    }

    public int getArtifactZoneLimit() {
        return this.artifactZoneLimit;
    }

    public int getReserveZoneLimit() {
        return this.reserveZoneLimit;
    }

    public int getInitialHandSize() {
        return this.initialHandSize;
    }

    public int getInitialPoints() {
        return this.initialPoints;
    }

    public abstract Player addPlayer(User user, IDeck deck);

    protected void checkRepeatedCards(IDeck deck, int maxRepeatedCards) {
        HashMap<CardName, Integer> countEachCard = deck.getRepeatedCards();
        countEachCard.forEach((cardType, cardCount) -> {
            if (cardCount > maxRepeatedCards) {
                throw new InvalidDeckCount("El mazo no puede tener mas de 3 copias de cada carta");
            }
        });
    }

    protected void checkDecks(IDeck deck) {
        if (deck.getCards().size() < MINIMUM_CARDS_PER_DECK || deck.getCards().size() > MAXIMUM_CARDS_PER_DECK) {
            throw new InvalidDeckCount("El mazo debe tener entre 40 y 60 cartas");
        }
    }
}