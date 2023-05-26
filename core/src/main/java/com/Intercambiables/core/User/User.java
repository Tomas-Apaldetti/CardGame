package com.Intercambiables.core.User;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Market.*;
import com.Intercambiables.core.Market.Exception.UnknownTransactionableItemException;

import java.util.ArrayList;
import java.util.List;

public class User implements IBuyer, ISeller {

    private final String userName;
    private DeckInventory deckInventory;
    private IWallet wallet;

    private final List<Card> cards;

    User(String userName) {
        this.userName = userName;
        this.deckInventory = new DeckInventory();
        this.wallet = new UserWallet();
        this.cards = new ArrayList<>();
    }

    public String getUserName() {
        return this.userName;
    }

    public DeckInventory getDeckInventory() {
        return this.deckInventory;
    }

    @Override
    public void addItem(ITransactionable item) {
        throw new UnknownTransactionableItemException();
    }

    @Override
    public void subtract(Amount value) {
        this.wallet.subtract(value.value());
    }

    public void addItem(Card card){
        this.cards.add(card);
    }

    @Override
    public boolean hasEnoughFounds(Amount value) {
        return wallet.hasEnoughFounds(value);
    }

    @Override
    public void removeItem(ITransactionable item) {
        throw new UnknownTransactionableItemException();
    }

    @Override
    public void credit(Amount value) {
        this.wallet.add(value.value());
    }

    public void removeItem(Card card) {
        this.cards.remove(card);
    }
}
