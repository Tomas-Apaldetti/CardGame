package com.core.g3.Card.Attack.Mocks;

import java.util.List;

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
        Card card = new Card(CardName.Corrosion, false);
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

    @Override
    public List<CardName> getCards() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCards'");
    }

    @Override
    public void forceOrder(List<CardName> cards) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'forceOrder'");
    }

    @Override
    public void putCardOnTop(ICard card) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putCardOnTop'");
    }
}
