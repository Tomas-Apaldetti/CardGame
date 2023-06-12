package com.core.g3.Match.DeckPlayable;

import com.core.g3.Card.CardName;
import com.core.g3.Deck.ICard;

import java.util.List;

public interface IDeckPlayable {

    public ICard getCard();

    public void shuffle();

    public int size();

    public List<CardName> getCards();

    public void forceOrder(List<CardName> cards);
}
