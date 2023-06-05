package com.core.g3.Deck;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.Collection;

import com.core.g3.Card.CardName;
import com.core.g3.Deck.Exceptions.CardAlreadyExistsInDeckException;

public class Deck implements IDeckModifiable {

    private String deckName;

    private final HashMap<CardName, List<ICard>> cards;

    public Deck(String deckName) {
        this.deckName = deckName;
        this.cards = new HashMap<>();
    }

    @Override
    public void setDeckName(String newDeckName) {
        this.deckName = newDeckName;
    }

    @Override
    public String getDeckName() {
        return this.deckName;
    }

    public void addCard(ICard card) {
        if (!this.cards.containsKey(card.getName())) {
            this.cards.put(card.getName(), new ArrayList<ICard>());
        }

        List<ICard> existing = this.cards.get(card.getName());

        if (existing.contains(card)) {
            throw new CardAlreadyExistsInDeckException();
        }

        existing.add(card);
    }

    public void addCards(Collection<ICard> cards) {
        for (ICard card : cards) {
            this.addCard(card);
        }
    }

    public Collection<ICard> getCards() {
        return this.cards.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public void removeCard(ICard card) {
        if (this.cards.containsKey(card.getName())) {
            this.cards.get(card.getName()).remove(card);
        }
    }

    public HashMap<CardName, Integer> getRepeatedCards() {
        HashMap<CardName, Integer> countForEachCard = new HashMap<>();

        cards.forEach((cardName, cardList) -> {
            Integer cardsSize = cardList.get(0).shouldCountAgainstNameLimit() ? cardList.size() : 0;
            countForEachCard.put(cardName, cardsSize);
        });

        return countForEachCard;
    }
}
