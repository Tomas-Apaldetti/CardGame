package com.core.Card.Cost;

import com.core.Card.Card;
import com.core.Card.CardName;
import com.core.Deck.ICard;
import com.core.Match.DeckPlayable.IDeckPlayable;

public class DeckPlayableMock implements IDeckPlayable {

    @Override
    public ICard getCard() {
        return new Card(CardName.Alquimista, false);
    }

    @Override
    public void shuffle() {
        return;
    }
}
