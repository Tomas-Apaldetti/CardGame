package com.core.g3.Deck;

import java.util.Collection;

public interface IDeck {

    public String getDeckName();

    void removeCard(ICard card);

    Collection<ICard> getCards();

    void addCards(Collection<ICard> cards);

    void addCard(ICard card);
}
