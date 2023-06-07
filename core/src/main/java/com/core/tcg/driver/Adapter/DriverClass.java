package com.core.tcg.driver.Adapter;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Deck.IDeck;
import com.core.g3.Match.GameMode.GameMode;
import com.core.g3.Match.GameMode.GameMode1;
import com.core.g3.Match.GameMode.GameMode2;
import com.core.g3.Match.Match;
import com.core.g3.Match.Player.Player;
import com.core.g3.User.User;
import com.core.tcg.driver.*;

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
        return account.countCards(MapCardName.from(cardName));
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
        account.buyCards(MapCardName.from(cardName),amount);
    }

    @Override
    public int countDeckCards(User account, String deckName, DriverCardName cardName) {
        IDeck deck = account.getDeckInventory().getDeck(deckName);
        Integer count = 0;
        for (ICard card : deck.getCards()) {
            CardName cardType = CardName.values()[cardName.ordinal()];
            if (card.getName().equals(cardType)) {
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
            CardName name = CardName.values()[cardName.ordinal()];
            Card card = new Card(name, true);
            deck.addCard(card);
        }
    }

    @Override
    public MatchDriver<Card> startMatch(DriverGameMode mode, User blue, String blueDeck, User green, String greenDeck) {

        if (mode.equals(DriverGameMode.HitpointLoss)) {
            GameMode gamemode = new GameMode1();
            IDeck gDeck = green.getDeckInventory().getDeck(greenDeck);
            Player greenPlayer = gamemode.addPlayer(green,gDeck);

            IDeck bDeck = green.getDeckInventory().getDeck(blueDeck);
            Player bluePlayer = gamemode.addPlayer(blue,bDeck);
            Match match = new Match(bluePlayer, greenPlayer, gamemode);
            return new MatchDriverClass(match);
        }
        if(mode.equals(DriverGameMode.CreatureSlayer)){
            GameMode gamemode = new GameMode2();
            IDeck gDeck = green.getDeckInventory().getDeck(greenDeck);
            Player greenPlayer = gamemode.addPlayer(green,gDeck);

            IDeck bDeck = green.getDeckInventory().getDeck(blueDeck);
            Player bluePlayer = gamemode.addPlayer(blue,bDeck);
            Match match = new Match(bluePlayer, greenPlayer, gamemode);
            return new MatchDriverClass(match);
        }
        return null;
    }
}

