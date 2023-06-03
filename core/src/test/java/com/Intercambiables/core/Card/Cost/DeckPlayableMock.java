package com.Intercambiables.core.Card.Cost;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Card.CardName;
import com.Intercambiables.core.Deck.ICard;
import com.Intercambiables.core.Match.DeckPlayable.IDeckPlayable;

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
