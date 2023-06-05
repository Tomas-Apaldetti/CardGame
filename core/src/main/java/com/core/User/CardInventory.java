package com.core.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import com.core.Deck.ICard;
import com.core.User.Exceptions.DuplicatedCardReferenceException;

public class CardInventory {

    List<ICard> cards;

    public CardInventory() {
        this.cards = new ArrayList<ICard>();
    }

    public Collection<ICard> getCards() {
        return this.cards;
    }

    public void removeCard(ICard card) {
        this.cards.remove(card);
    }

    public void addCard(ICard card) {
        if (this.cards.contains(card)) {
            throw new DuplicatedCardReferenceException();
        }

        this.cards.add(card);
    }

}
