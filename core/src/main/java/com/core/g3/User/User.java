package com.core.g3.User;

import java.util.Collection;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Deck.IDeck;
import com.core.g3.Market.*;
import com.core.g3.Market.Exceptions.InsufficientMoneyException;
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
    public void buyCards(CardName name, int amount) {
        Card card = new CardBuilder(name).build();
        Amount amountToSubstract = new Amount(card.getPrice() * amount);
        if (wallet.hasEnoughFounds(amountToSubstract)) {
            wallet.subtract(amountToSubstract.value());
            for (int i = 0; i < amount; i++) {
                Card cardToAdd = new CardBuilder(name).build();
                this.cardInventory.addCard(cardToAdd);
            }
        } else {
            throw new InsufficientMoneyException();
        }
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

    public int countCards(CardName name) {
        return this.cardInventory.countCards(name);
    }

    public int countDeckSpecificCards(String deckName, CardName name) {
        IDeck deck = deckInventory.getDeck(deckName);
        Integer count = 0;
        for (ICard card : deck.getCards()) {
            if (card.getName().equals(name)) {
                count++;
            }
        }
        return count;
    }

    public void addCardToDeck(String deckName, CardName name, int amount) {
        IDeck deck = deckInventory.getOrCreateDeck(deckName);

        Collection<ICard> cards = cardInventory.getCardsByName(name, amount);
        deck.addCards(cards);
    }
}
