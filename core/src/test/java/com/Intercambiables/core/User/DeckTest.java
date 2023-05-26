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

        IDeck deck = usr.getDeckInventory().createDeck("mazo de test");

        assertEquals(1, usr.getDeckInventory().getDecks().size());
        assertEquals("mazo de test", deck.getDeckName());
    }

    @Test(expected = DeckAlreadyExistsException.class)
    public void createDuplicateDeckFails() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        usr.getDeckInventory().createDeck("mazo de test");

        usr.getDeckInventory().createDeck("mazo de test");
    }

    @Test
    public void createDeckSavePointer() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.getDeckInventory().createDeck("mazo de test");

        assertEquals(deck, usr.getDeckInventory().getDeck("mazo de test"));
    }

    @Test(expected = DeckDoesntExistException.class)
    public void nonExistentDeckPointerIsNull() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        usr.getDeckInventory().getDeck("mazo de test");
    }

    @Test
    public void removeDeck() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.getDeckInventory().createDeck("mazo de test");

        usr.getDeckInventory().removeDeck("mazo de test");

        assertEquals(0, usr.getDeckInventory().getDecks().size());

    }

    @Test
    public void removeNonExistentDeckDoesntFails() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.getDeckInventory().createDeck("mazo de test");

        usr.getDeckInventory().removeDeck("mazo de test");

        usr.getDeckInventory().removeDeck("mazo de test");

        assertEquals(0, usr.getDeckInventory().getDecks().size());
    }

    @Test
    public void initialUserDoesntHaveDecks() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        assertEquals(0, usr.getDeckInventory().getDecks().size());
    }

    @Test
    public void updateDeck() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.getDeckInventory().createDeck("mazo de test");

        usr.getDeckInventory().updateDeck("mazo de test", "mazo de test V.2");

        assertEquals(deck, usr.getDeckInventory().getDeck("mazo de test V.2"));
    }

    @Test(expected = DeckDoesntExistException.class)
    public void updatedDeckDoesntHaveTheOldNameAnymore() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.getDeckInventory().createDeck("mazo de test");

        usr.getDeckInventory().updateDeck("mazo de test", "mazo de test V.2");

        usr.getDeckInventory().getDeck("mazo de test");
    }
}
