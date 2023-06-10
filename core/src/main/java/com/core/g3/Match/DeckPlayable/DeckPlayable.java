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
        for (ICard card: cards){
            names.add(card.getName());
        }
        return names;
    }

    public void forceOrder(List<CardName> names){
        HashMap<CardName,List<Integer>> hash = new HashMap<>();
        Integer i = 0;
        for(CardName name: names){
            List<Integer> positions = hash.containsKey(name) ? hash.get(name) : new ArrayList<>();
            positions.add(i);
            hash.put(name,positions);
            i++;
        }
        orderCard(hash,cards.get(0),0);
    }

    private void orderCard(HashMap<CardName, List<Integer>> hash, ICard card, int i) {
        if(hash.get(card.getName()).size() == 0){
            if(i != this.cards.size() - 1){
                i++;
                orderCard(hash,this.cards.get(i),i);
            }
            else {
                return;
            }
        }
        Integer pos = hash.get(card.getName()).remove(0);
        ICard toMove = this.cards.get(i);
        this.cards.add(i,toMove);
        this.cards.add(pos,card);
        orderCard(hash, toMove, i);
    }
}
