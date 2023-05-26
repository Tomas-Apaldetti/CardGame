package com.Intercambiables.core.User;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.Intercambiables.core.Deck.IDeck;
import com.Intercambiables.core.Deck.Exceptions.DeckAlreadyExistsException;
import com.Intercambiables.core.User.Exceptions.DeckDoesntExistException;

@SpringBootTest
public class DeckTest {

    @Test
    public void createDeck() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.createDeck("mazo de test");

        assertEquals(1, usr.getDecks().size());
        assertEquals("mazo de test", deck.getDeckName());
    }

    @Test(expected = DeckAlreadyExistsException.class)
    public void createDuplicateDeckFails() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        usr.createDeck("mazo de test");

        usr.createDeck("mazo de test");
    }

    @Test
    public void createDeckSavePointer() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.createDeck("mazo de test");

        assertEquals(deck, usr.getDeck("mazo de test"));
    }

    @Test(expected = DeckDoesntExistException.class)
    public void nonExistentDeckPointerIsNull() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        usr.getDeck("mazo de test");
    }

    @Test
    public void removeDeck() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.createDeck("mazo de test");

        usr.removeDeck("mazo de test");

        assertEquals(0, usr.getDecks().size());

    }

    @Test
    public void removeNonExistentDeckDoesntFails() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.createDeck("mazo de test");

        usr.removeDeck("mazo de test");

        usr.removeDeck("mazo de test");

        assertEquals(0, usr.getDecks().size());
    }

    @Test
    public void initialUserDoesntHaveDecks() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        assertEquals(0, usr.getDecks().size());
    }

    @Test
    public void updateDeck() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.createDeck("mazo de test");

        usr.updateDeck("mazo de test", "mazo de test V.2");

        assertEquals(deck, usr.getDeck("mazo de test V.2"));
    }

    @Test(expected = DeckDoesntExistException.class)
    public void updatedDeckDoesntHaveTheOldNameAnymore() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.createDeck("mazo de test");

        usr.updateDeck("mazo de test", "mazo de test V.2");

        usr.getDeck("mazo de test");
    }
}
