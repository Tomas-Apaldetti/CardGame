package com.core.g3.Match.DeckPlayable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void forceOrder(List<CardName> names) {

        // creating a map of cards as key card name and value list of ICards
        Map<CardName, List<ICard>> cardMap = new HashMap<>();
        for (ICard card : this.cards) {
            CardName name = card.getName();
            cardMap.putIfAbsent(name, new ArrayList<>());
            cardMap.get(name).add(card);
        }

        List<ICard> orderedList = new ArrayList<>();
        for (CardName cardName : names) {
            if (cardMap.containsKey(cardName) && !cardMap.get(cardName).isEmpty()) {
                orderedList.add(cardMap.get(cardName).remove(0));
            }
        }
    
        this.cards = orderedList;
    }
}
