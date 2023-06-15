package com.core.g3.User;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardFactory;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;

public class UserTest {
    @Test
    public void testUserBuyAndAddCards() {
        User user = new User("user");
        user.credit(new Amount(10));
        CardName cardName = CardName.Alchemist;
        Card newCardToBuy = CardFactory.createCard(cardName);
        user.buyCards(cardName,1);
        user.addCardToDeck("deck", cardName, 1);
        assert (user.countCards(cardName) == 1);
    }

    @Test
    public void testUserBuyAndAddSeveralSameTypeCards() {
        User user = new User("user");
        user.credit(new Amount(10));
        CardName cardName = CardName.Alchemist;
        user.buyCards(cardName,3);
        user.addCardToDeck("deck", cardName, 2);
        assert (user.countCards(cardName) == 3);
        assert (user.getDeckInventory().getDeck("deck").getCards().size() == 2);
    }

    @Test
    public void testFullSizeDeck() {
        User user = new User("user");
        user.credit(new Amount(999999));

        // Create a list with some CardName values three times each to iterate over
        // and add to the deck
        CardName[] cardNames = CardName.values();

        for (int i = 0; i < cardNames.length; i++) {
            CardName cardName = cardNames[i];
            user.buyCards(cardName,3);

            user.addCardToDeck("deck", cardName, 3);
        }
        System.out.println(user.getDeckInventory().getDeck("deck").getCards().size());

        assert (user.getDeckInventory().getDeck("deck").getCards().size() == 60);
    }
}
