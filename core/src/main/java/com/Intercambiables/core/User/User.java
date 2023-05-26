package com.Intercambiables.core.User;

import java.util.HashMap;

import org.aspectj.weaver.loadtime.definition.Definition.DeclareAnnotationKind;

import java.util.Collection;

import com.Intercambiables.core.Deck.Deck;
import com.Intercambiables.core.Deck.IDeck;
import com.Intercambiables.core.Deck.Exceptions.DeckAlreadyExistsException;
import com.Intercambiables.core.User.Exceptions.DeckDoesntExistException;

public class User {

    private final String userName;

    private final HashMap<String, IDeck> deck;

    User(String userName) {
        this.userName = userName;
        this.deck = new HashMap<>();
    }

    public String getUserName() {
        return this.userName;
    }

    public IDeck createDeck(String deckName) {
        if (this.existsDeck(deckName)) {
            throw new DeckAlreadyExistsException();
        }

        IDeck deck = new Deck(deckName);

        this.insertDeck(deck);

        return deck;
    }

    private boolean existsDeck(String deckName) {
        return this.deck.containsKey(deckName);
    }

    public void removeDeck(String deckName) {
        this.deck.remove(deckName);
    }

    public Collection<IDeck> getDecks() {
        return this.deck.values();
    }

    public IDeck getDeck(String deckName) {
        if (!this.existsDeck(deckName)) {
            throw new DeckDoesntExistException();
        }

        return this.deck.get(deckName);
    }

    public void updateDeck(String oldDeckName, String newDeckName) {
        IDeck deck = this.getDeck(oldDeckName);

        this.removeDeck(oldDeckName);

        deck.setDeckName(newDeckName);

        this.insertDeck(deck);
    }

    private void insertDeck(IDeck deck) {
        this.deck.put(deck.getDeckName(), deck);
    }

}
