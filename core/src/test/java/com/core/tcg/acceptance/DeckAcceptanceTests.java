package com.core.tcg.acceptance;


import com.core.tcg.driver.DriverCardName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckAcceptanceTests<Account, Card> extends AcceptanceTestRoot<Account, Card> {

    @Test
    void countAddedCards() {
        String deckName = "main";
        DriverCardName card1 = DriverCardName.WaterEnergy;
        DriverCardName card2 = DriverCardName.Orc;

        Account account = testDriver.newAccount();
        testDriver.addCurrency(account, Integer.MAX_VALUE);
        testDriver.buyCards(account, card1, 1);
        testDriver.buyCards(account, card2, 2);
        testDriver.addDeckCards(account, deckName, card1, 1);
        testDriver.addDeckCards(account, deckName, card2, 2);

        assertEquals(1, testDriver.countDeckCards(account, deckName, card1));
        assertEquals(2, testDriver.countDeckCards(account, deckName, card2));
    }

    @Test
    void decksHaveSeparateCards() {
        String deckName1 = "main";
        String deckName2 = "side";
        DriverCardName card = DriverCardName.WaterEnergy;

        Account account = testDriver.newAccount();
        testDriver.addCurrency(account, Integer.MAX_VALUE);
        testDriver.buyCards(account, card, 3);
        testDriver.addDeckCards(account, deckName1, card, 1);
        testDriver.addDeckCards(account, deckName2, card, 2);

        assertEquals(1, testDriver.countDeckCards(account, deckName1, card));
        assertEquals(2, testDriver.countDeckCards(account, deckName2, card));
    }

    @Test
    void accountsHaveSeparateDeckCards() {
        String deckName = "main";
        DriverCardName card = DriverCardName.WaterEnergy;

        Account account1 = testDriver.newAccount();
        Account account2 = testDriver.newAccount();
        testDriver.addCurrency(account1, Integer.MAX_VALUE);
        testDriver.addCurrency(account2, Integer.MAX_VALUE);
        testDriver.buyCards(account1, card, 1);
        testDriver.buyCards(account2, card, 2);
        testDriver.addDeckCards(account1, deckName, card, 1);
        testDriver.addDeckCards(account2, deckName, card, 2);

        assertEquals(1, testDriver.countDeckCards(account1, deckName, card));
        assertEquals(2, testDriver.countDeckCards(account2, deckName, card));
    }
}
