package com.Intercambiables.core.driver;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Market.Amount;
import com.Intercambiables.core.User.User;

import java.nio.charset.Charset;
import java.util.Random;

public class DriverClass implements Driver<User, Card>{
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

    }

    @Override
    public int countDeckCards(User account, String deckName, DriverCardName cardName) {
        return 0;
    }

    @Override
    public void addDeckCards(User account, String deckName, DriverCardName cardName, int amount) {

    }

    @Override
    public MatchDriver<Card> startMatch(DriverGameMode mode, User blue, String blueDeck, User green, String greenDeck) {
        return null;
    }
}
