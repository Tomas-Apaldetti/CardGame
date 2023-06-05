package com.core.g3.Card.Cost;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.DeckPlayable.IDeckPlayable;

public class DeckPlayableMock implements IDeckPlayable {

    @Override
    public ICard getCard() {
        return new Card(CardName.Alchemist, false);
    }

    @Override
    public void shuffle() {
        return;
    }

    @Override
    public int size() {
        return 0;
    }
}
