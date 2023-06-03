package com.Intercambiables.core.acceptance;


import com.Intercambiables.core.driver.Driver;
import com.Intercambiables.core.driver.DriverCardName;
import com.Intercambiables.core.driver.DriverGameMode;
import com.Intercambiables.core.driver.MatchDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AcceptanceTestRoot<Account, Card> {
    protected Driver<Account, Card> testDriver = new Driver<Account, Card>() {
        @Override
        public Account newAccount() {
            return null;
        }

        @Override
        public int countCards(Account account, DriverCardName cardName) {
            return 0;
        }

        @Override
        public int availableCurrency(Account account) {
            return 0;
        }

        @Override
        public void addCurrency(Account account, int amount) {

        }

        @Override
        public void buyCards(Account account, DriverCardName cardName, int amount) {

        }

        @Override
        public int countDeckCards(Account account, String deckName, DriverCardName cardName) {
            return 0;
        }

        @Override
        public void addDeckCards(Account account, String deckName, DriverCardName cardName, int amount) {

        }

        @Override
        public MatchDriver<Card> startMatch(DriverGameMode mode, Account blue, String blueDeck, Account green, String greenDeck) {
            return null;
        }
    };
}