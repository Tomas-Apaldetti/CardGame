package com.Intercambiables.core.User;

public class User {

    private final String userName;
    public DeckInventory deckInventory;

    User(String userName) {
        this.userName = userName;
        this.deckInventory = new DeckInventory();
    }

    public String getUserName() {
        return this.userName;
    }

    public DeckInventory getDeckInventory() {
        return this.deckInventory;
    }

}
