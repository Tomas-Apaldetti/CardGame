package com.core.g3.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import com.core.g3.Deck.ICard;
import com.core.g3.User.Exceptions.DuplicatedCardReferenceException;

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
