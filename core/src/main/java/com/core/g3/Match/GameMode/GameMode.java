package com.core.g3.Match.GameMode;

import com.core.g3.Card.CardName;
import com.core.g3.Deck.Deck;
import com.core.g3.Match.GameMode.Exceptions.InvalidDeckCount;

import java.util.HashMap;



public abstract class GameMode {
    private int combatZoneLimit;
    private int artifactZoneLimit;
    private int reserveZoneLimit;
    private int initialHandSize = 0;
    protected int MAXIMUM_CARDS_PER_DECK;
    protected int MINIMUM_CARDS_PER_DECK;
    protected int MAXIMUM_REPEATED_CARDS;


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

    protected void checkRepeatedCards(Deck deck, int maxRepeatedCards) {
        HashMap<CardName, Integer> countEachCard = deck.getRepeatedCards();
        countEachCard.forEach((cardType, cardCount) -> {
            if (cardCount > maxRepeatedCards) {
                 throw new InvalidDeckCount("El mazo no puede tener mas de 3 copias de cada carta");
            }
        });
    }

    protected void checkDecks(Deck deck) {
        if (deck.getCards().size() < MINIMUM_CARDS_PER_DECK || deck.getCards().size() > MAXIMUM_CARDS_PER_DECK) {
            throw new InvalidDeckCount("El mazo debe tener entre 40 y 60 cartas");
        }
    }
}