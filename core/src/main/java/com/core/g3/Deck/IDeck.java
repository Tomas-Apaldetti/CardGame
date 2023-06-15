package com.core.g3.Deck;

import com.core.g3.Card.CardName;

import java.util.Collection;
import java.util.HashMap;

public interface IDeck {

    public String getDeckName();

    void removeCard(ICard card);

    Collection<ICard> getCards();

    void addCards(Collection<ICard> cards); // TODO -> move?

    void addCard(ICard card);

    public HashMap<CardName, Integer> getRepeatedCards();
}
