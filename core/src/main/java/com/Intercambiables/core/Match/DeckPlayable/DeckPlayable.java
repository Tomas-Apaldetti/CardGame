package com.Intercambiables.core.Match.DeckPlayable;
import java.util.Collections;
import java.util.List;

import com.Intercambiables.core.Deck.ICard;
import com.Intercambiables.core.Deck.IDeck;


public class DeckPlayable implements IDeckPlayable {
    private IDeck deck;
    private List<ICard> cards;

    public DeckPlayable(IDeck deck) {
        this.deck = deck;
        this.cards = (List<ICard>) deck.getCards();
        this.shuffle();
    }

    @Override
    public ICard getCard() {
        if (this.cards.isEmpty()) {
            throw new EmptyDeckPlayableException();
        }
        return this.cards.remove(0);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }
}
