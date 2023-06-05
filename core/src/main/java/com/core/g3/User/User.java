package com.core.g3.User;

import java.util.Collection;

import com.core.g3.Card.Card;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Market.*;
import com.core.g3.Market.Transactions.IBuyer;
import com.core.g3.Market.Transactions.ISeller;
import com.core.g3.Match.IAccount;

public class User implements IBuyer, ISeller, IAccount {

    private final String userName;
    private DeckInventory deckInventory;
    private IWallet wallet;

    private CardInventory cardInventory;

    public User(String userName) {
        this.userName = userName;
        this.deckInventory = new DeckInventory();
        this.wallet = new UserWallet();
        this.cardInventory = new CardInventory();
    }

    public String getUserName() {
        return this.userName;
    }

    public DeckInventory getDeckInventory() {
        return this.deckInventory;
    }

    public CardInventory getCardInventory() {
        return this.cardInventory;
    }

    @Override
    public void subtract(Amount value) {
        this.wallet.subtract(value.value());
    }

    @Override
    public void addItem(Card card) {
        this.cardInventory.addCard(card);
    }

    @Override
    public boolean hasEnoughFounds(Amount value) {
        return wallet.hasEnoughFounds(value);
    }

    @Override
    public void credit(Amount value) {
        this.wallet.add(value.value());
    }

    @Override
    public void removeItem(Card card) {
        this.cardInventory.removeCard(card);
    }

    public Collection<ICard> getCards() {
        return this.cardInventory.getCards();
    }

    public int getFounds() {
        return this.wallet.money();
    }
}
