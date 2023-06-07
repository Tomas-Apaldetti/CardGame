package com.core.g3.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Collection;

import com.core.g3.Card.CardName;
import com.core.g3.Deck.ICard;
import com.core.g3.User.Exceptions.DuplicatedCardReferenceException;
import com.core.g3.User.Exceptions.NotEnoughCardsException;

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

    public Collection<ICard> getCardsByName(CardName name, int amount) {
        Collection<ICard> cards = new HashSet<>();
        for(ICard card : this.cards){
            if(cards.size() == amount){
                return cards;
            }
            if( card.getName().equals(name)) {
                cards.add(card);
            }
        }
        if(cards.size() == amount){
            return cards;
        }
        throw new NotEnoughCardsException();
    }

    public int countCards(CardName name) {
        int size = 0;
        for(ICard card : this.cards){
            if( card.getName().equals(name)){
                size += 1;
            }
        }
        return size;
    }
}
