package com.core.g3.User;

import org.junit.jupiter.api.Test;

import com.core.g3.Deck.IDeck;
import com.core.g3.Deck.Exceptions.DeckAlreadyExistsException;
import com.core.g3.User.Exceptions.DeckDoesntExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckInventoryTest {

    @Test
    public void createDeck() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.getDeckInventory().createDeck("mazo de test");

        assertEquals(1, usr.getDeckInventory().getDecks().size());
        assertEquals("mazo de test", deck.getDeckName());
    }

    @Test
    public void createDuplicateDeckFails() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        usr.getDeckInventory().createDeck("mazo de test");

        assertThrows(DeckAlreadyExistsException.class, () -> usr.getDeckInventory().createDeck("mazo de test"));
    }

    @Test
    public void createDeckSavePointer() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        IDeck deck = usr.getDeckInventory().createDeck("mazo de test");

        assertEquals(deck, usr.getDeckInventory().getDeck("mazo de test"));
    }

    @Test
    public void nonExistentDeckPointerIsNull() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        assertThrows(DeckDoesntExistException.class, () -> usr.getDeckInventory().getDeck("mazo de test"));
    }

    @Test
    public void removeDeck() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        usr.getDeckInventory().createDeck("mazo de test");

        usr.getDeckInventory().removeDeck("mazo de test");

        assertEquals(0, usr.getDeckInventory().getDecks().size());

    }

    @Test
    public void removeNonExistentDeckDoesntFails() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        usr.getDeckInventory().createDeck("mazo de test");

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

    @Test
    public void updatedDeckDoesntHaveTheOldNameAnymore() {
        User usr = TestUserRegister.createUser("caro", "caro&fran");

        usr.getDeckInventory().createDeck("mazo de test");

        usr.getDeckInventory().updateDeck("mazo de test", "mazo de test V.2");

        assertThrows(DeckDoesntExistException.class, () -> usr.getDeckInventory().getDeck("mazo de test"));
    }
}
