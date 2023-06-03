package com.Intercambiables.core.Deck;
import java.util.Collections;
import java.util.List;


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
        return this.cards.remove(0);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

}
