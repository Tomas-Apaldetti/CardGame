package com.core.g3.Match.DeckPlayable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.core.g3.Card.CardName;
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

    public List<CardName> getCards() {
        List<CardName> names = new ArrayList<>();
        for (ICard card : cards) {
            names.add(card.getName());
        }
        return names;
    }

    private void orderCard(HashMap<CardName, List<Integer>> hash, ICard card, Integer i,
            List<ICard> newOrderedList) {
        if (hash.containsKey(card.getName())) {
            List<Integer> positions = hash.get(card.getName());
            if (positions.size() > 0) {
                Integer position = positions.remove(0);
                ICard cardToSwap = this.cards.get(position);
                newOrderedList.set(position, card);
                newOrderedList.set(i, cardToSwap);
            }
        }
        if (i < this.cards.size() - 1) {
            orderCard(hash, this.cards.get(i + 1), i + 1, newOrderedList);
        }
    }

    public void forceOrder(List<CardName> names) {
        HashMap<CardName, List<Integer>> hash = new HashMap<>();
        Integer i = 0;
        for (CardName name : names) {
            List<Integer> positions = hash.containsKey(name) ? hash.get(name) : new ArrayList<>();
            positions.add(i);
            hash.put(name, positions);
            i++;
        }
        System.out.println(hash);
        List<ICard> newOrderedList = this.cards;
        orderCard(hash, this.cards.get(0), 0, newOrderedList);
        this.cards = newOrderedList;
    }
}
