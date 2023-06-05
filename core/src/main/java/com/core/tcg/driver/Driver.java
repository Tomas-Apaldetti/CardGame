package com.core.tcg.driver;

/**
 * Facade for the TCG game, to allow for multiple implementations.
 */
public interface Driver<AccountReference, CardReference> {
    /**
     * @return Reference to a new, unused account
     */
    AccountReference newAccount();

    /**
     * @return Number of cardName in the account
     */
    int countCards(AccountReference account, DriverCardName cardName);

    /**
     * @return Amount of currency in the account.
     */
    int availableCurrency(AccountReference account);

    /**
     * Add an amount of currency to the account.
     * Should only be called with positive amounts.
     */
    void addCurrency(AccountReference account, int amount);

    /**
     * Add an amount of a card to the account, while removing some
     * (unspecified) amount of currency from it.
     * Should only be called with positive amounts.
     */
    void buyCards(AccountReference account, DriverCardName cardName, int amount);

    /**
     * @return Number of cardName in the account's deck deckName
     */
    int countDeckCards(AccountReference account, String deckName, DriverCardName cardName);

    /**
     * Add an amount of a card to a deck in an account.
     * Should only be called with positive amounts.
     */
    void addDeckCards(AccountReference account, String deckName, DriverCardName cardName, int amount);

    /**
     * Start a match between two players.
     * 
     * @throws RuntimeException if either deck is invalid for the game mode
     */
    MatchDriver<CardReference> startMatch(
            DriverGameMode mode,
            AccountReference blue,
            String blueDeck,
            AccountReference green,
            String greenDeck);
}
