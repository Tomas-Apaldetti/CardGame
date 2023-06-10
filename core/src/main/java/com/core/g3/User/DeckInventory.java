package com.core.g3.User;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Collection;

import com.core.g3.Deck.Deck;
import com.core.g3.Deck.IDeck;
import com.core.g3.Deck.IDeckModifiable;
import com.core.g3.Deck.Exceptions.DeckAlreadyExistsException;
import com.core.g3.User.Exceptions.DeckDoesntExistException;

public class DeckInventory {

    private final HashMap<String, IDeckModifiable> decks;

    DeckInventory() {
        this.decks = new HashMap<>();
    }

    public IDeck createDeck(String deckName) {
        if (this.existsDeck(deckName)) {
            throw new DeckAlreadyExistsException();
        }

        IDeckModifiable deck = new Deck(deckName);

        this.insertDeck(deck);

        return deck;
    }

    private boolean existsDeck(String deckName) {
        return this.decks.containsKey(deckName);
    }

    public void removeDeck(String deckName) {
        this.decks.remove(deckName);
    }

    public Collection<IDeck> getDecks() {
        List<IDeck> decks = this.decks.values()
                .stream()
                .map(e -> (IDeck) e)
                .collect(Collectors.toList());
        return decks;
    }

    public IDeck getDeck(String deckName) {
        return this._getDeck(deckName);
    }

    private IDeckModifiable _getDeck(String deckName) {
        if (!this.existsDeck(deckName)) {
            throw new DeckDoesntExistException();
        }

        return this.decks.get(deckName);
    }

    public void updateDeck(String oldDeckName, String newDeckName) {
        IDeckModifiable deck = this._getDeck(oldDeckName);

        this.removeDeck(oldDeckName);

        deck.setDeckName(newDeckName);

        this.insertDeck(deck);
    }

    private void insertDeck(IDeck deck) {
        this.decks.put(deck.getDeckName(), (IDeckModifiable) deck);
    }

    public IDeck getOrCreateDeck(String deckName) {
        if (!this.existsDeck(deckName)) {
            this.createDeck(deckName);
        }

        return this.decks.get(deckName);
    }
}
