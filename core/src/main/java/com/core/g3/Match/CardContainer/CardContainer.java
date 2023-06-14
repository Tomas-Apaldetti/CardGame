package com.core.g3.Match.CardContainer;

import com.core.g3.Card.CardName;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardContainer.Exception.CardAlreadyOnPlaceException;
import com.core.g3.Match.CardContainer.Exception.CardNotOnPlaceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardContainer {

    private List<ICard> cards;

    public CardContainer() {
        this.cards = new ArrayList<>();
    }

    public List<ICard> peek() {
        return Collections.unmodifiableList(this.cards);
    }

    public void add(ICard card) {
        if (this.cards.contains(card)) {
            throw new CardAlreadyOnPlaceException();
        }
        this.cards.add(card);
    }

    public List<ICard> removeAll() {
        List<ICard> cards = this.cards;
        this.cards = new ArrayList<>();
        return cards;
    }

    public ICard remove(ICard card) {
        if (!this.cards.remove(card)) {
            throw new CardNotOnPlaceException();
        }
        return card;
    }

    public ICard getCardByName(CardName cardName) {
        // Returns the first card of type cardName in hand
        for (ICard card : this.cards) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        throw new CardNotOnPlaceException();
    }
}
