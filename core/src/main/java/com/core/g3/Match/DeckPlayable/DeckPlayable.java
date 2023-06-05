package com.core.g3.Match.DeckPlayable;

import java.util.Collections;
import java.util.List;

import com.core.g3.Deck.ICard;
import com.core.g3.Deck.IDeck;

public class DeckPlayable implements IDeckPlayable {
    private List<ICard> cards;

    public DeckPlayable(IDeck deck) {
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

    @Override
    public int size() {
        return this.cards.size();
    }
}
