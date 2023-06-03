package com.Intercambiables.core.acceptance;

import com.Intercambiables.core.driver.DriverCardName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ShopAcceptanceTests<Account, Card> extends AcceptanceTestRoot<Account, Card> {

    @Test
    void newAccountHasNoCurrency() {
        Account account = testDriver.newAccount();
        assertEquals(0, testDriver.availableCurrency(account));
    }

    @Test
    void newAccountHasNoCards() {
        Account account = testDriver.newAccount();
        Arrays.stream(DriverCardName.values()).forEach(name -> {
            int count = testDriver.countCards(account, name);
            assertEquals(0, count);
        });
    }

    @Test
    void buyingRemovesCurrency() {
        DriverCardName card = DriverCardName.WaterEnergy;

        Account account = testDriver.newAccount();
        testDriver.addCurrency(account, Integer.MAX_VALUE);
        testDriver.buyCards(account, card, 1);

        assertNotEquals(Integer.MAX_VALUE, testDriver.availableCurrency(account));
    }

    @Test
    void accountsHaveSeparateCurrency() {
        DriverCardName card = DriverCardName.WaterEnergy;

        Account account1 = testDriver.newAccount();
        Account account2 = testDriver.newAccount();
        testDriver.addCurrency(account1, Integer.MAX_VALUE);
        testDriver.addCurrency(account2, Integer.MAX_VALUE);
        testDriver.buyCards(account1, card, 1);

        assertNotEquals(Integer.MAX_VALUE, testDriver.availableCurrency(account1));
        assertEquals(Integer.MAX_VALUE, testDriver.availableCurrency(account2));
    }

    @Test
    void accountsHaveSeparateCards() {
        DriverCardName card = DriverCardName.WaterEnergy;

        Account account1 = testDriver.newAccount();
        Account account2 = testDriver.newAccount();
        testDriver.addCurrency(account1, Integer.MAX_VALUE);
        testDriver.addCurrency(account2, Integer.MAX_VALUE);
        testDriver.buyCards(account1, card, 1);
        testDriver.buyCards(account2, card, 2);

        assertEquals(1, testDriver.countCards(account1, card));
        assertEquals(2, testDriver.countCards(account2, card));
    }

    @Test
    void countBoughtCards() {
        DriverCardName card = DriverCardName.WaterEnergy;

        Account account = testDriver.newAccount();
        testDriver.addCurrency(account, Integer.MAX_VALUE);
        testDriver.buyCards(account, card, 1);
        testDriver.buyCards(account, card, 2);

        assertEquals(3, testDriver.countCards(account, card));
    }
}
