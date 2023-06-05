package com.core.g3.Deck;

import com.core.g3.Card.CardType;

import java.util.Collection;
import java.util.HashMap;

public interface IDeck {

    public String getDeckName();

    void removeCard(ICard card);

    Collection<ICard> getCards();

    void addCards(Collection<ICard> cards);

    void addCard(ICard card);

    public HashMap<CardType, Integer> getRepeatedCards();
}
