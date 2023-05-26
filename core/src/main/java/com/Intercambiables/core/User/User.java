package com.Intercambiables.core.User;

import com.Intercambiables.core.Market.IWallet;

public class User {

    private final String userName;
    private DeckInventory deckInventory;
    private IWallet wallet;

    User(String userName) {
        this.userName = userName;
        this.deckInventory = new DeckInventory();
        this.wallet = new UserWallet();
    }

    public String getUserName() {
        return this.userName;
    }

    public DeckInventory getDeckInventory() {
        return this.deckInventory;
    }

}
