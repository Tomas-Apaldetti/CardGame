package com.core.g3.Card.Action.Mocks;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Deck.Deck;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.DeckPlayable.IDeckPlayable;

public class PlayableDeckMock implements IDeckPlayable {
    public DeckPlayable playableDeck;

    public PlayableDeckMock() {
        Deck deck = new Deck("mazo_1");
        Card card = new Card(CardName.Antimagic, false);
        deck.addCard(card);
        playableDeck = new DeckPlayable(deck);
    }

    @Override
    public ICard getCard() {
        return this.playableDeck.getCard();
    }

    @Override
    public void shuffle() {
        this.playableDeck.shuffle();
    }

    @Override
    public int size() {
        return this.playableDeck.size();
    }
}
