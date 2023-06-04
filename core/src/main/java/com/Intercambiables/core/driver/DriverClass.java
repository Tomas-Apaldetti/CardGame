package com.Intercambiables.core.driver;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Deck.Deck;
import com.Intercambiables.core.Deck.ICard;
import com.Intercambiables.core.Deck.IDeck;
import com.Intercambiables.core.Market.Amount;
import com.Intercambiables.core.User.User;

import java.nio.charset.Charset;
import java.util.Random;

public class DriverClass implements Driver<User, Card> {
    @Override
    public User newAccount() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return new User(generatedString);
    }

    @Override
    public int countCards(User account, DriverCardName cardName) {
        return account.getCards().size();
    }

    @Override
    public int availableCurrency(User account) {
        return account.getFounds();
    }

    @Override
    public void addCurrency(User account, int amount) {
        account.credit(new Amount(amount));
    }

    @Override
    public void buyCards(User account, DriverCardName cardName, int amount) {
        // Substract "unspecified" amount of currency
        var unspecified_amount = 42;
        account.subtract(new Amount(unspecified_amount));
        for (int i = 0; i < amount; i++) {
            Card card = new Card(cardName, true);
            account.addItem(card);
        }
    }

    @Override
    public int countDeckCards(User account, String deckName, DriverCardName cardName) {
        IDeck deck = account.getDeckInventory().getDeck(deckName);
        Integer count = 0;
        for (ICard card : deck.getCards()) {
            if (card.getType().equals(cardName)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void addDeckCards(User account, String deckName, DriverCardName cardName, int amount) {
        IDeck deck;
        try {
            deck = account.getDeckInventory().getDeck(deckName);
        } catch (Exception e) {
            deck = account.getDeckInventory().createDeck(deckName);
        }
        // Create as many cards as amount parameter indicates
        for (int i = 0; i < amount; i++) {
            Card card = new Card(cardName, true);
            deck.addCard(card);
        }
    }

    @Override
    public MatchDriver<Card> startMatch(DriverGameMode mode, User blue, String blueDeck, User green, String greenDeck) {
        return null;
    }
}
