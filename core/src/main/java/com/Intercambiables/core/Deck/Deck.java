package com.Intercambiables.core.Deck;

public class Deck implements IDeck {

    public String deckName;

    public Deck(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public void setDeckName(String newDeckName) {
        this.deckName = newDeckName;
    }

    @Override
    public String getDeckName() {
        return this.deckName;
    }

}
