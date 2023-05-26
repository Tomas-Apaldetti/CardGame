package com.Intercambiables.core.User;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.Intercambiables.core.Deck.IDeck;
import com.Intercambiables.core.Deck.Exceptions.DeckAlreadyExistsException;

@SpringBootTest
public class DeckTest {

    @Test
    public void createDeck() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.createDeck("mazo de test");

        assertEquals("mazo de test", deck.getDeckName());
    }

    @Test(expected = DeckAlreadyExistsException.class)
    public void createDuplicateDeckFails() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        usr.createDeck("mazo de test");

        usr.createDeck("mazo de test");
    }

}
