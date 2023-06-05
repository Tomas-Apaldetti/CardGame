package com.core.User;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Collection;

import com.core.Deck.Deck;
import com.core.Deck.IDeck;
import com.core.Deck.IDeckModifiable;
import com.core.Deck.Exceptions.DeckAlreadyExistsException;
import com.core.User.Exceptions.DeckDoesntExistException;

public class DeckInventory {

    private final HashMap<String, IDeckModifiable> deck;

    DeckInventory() {
        this.deck = new HashMap<>();
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
        return this.deck.containsKey(deckName);
    }

    public void removeDeck(String deckName) {
        this.deck.remove(deckName);
    }

    public Collection<IDeck> getDecks() {
        List<IDeck> decks = this.deck.values()
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

        return this.deck.get(deckName);
    }

    public void updateDeck(String oldDeckName, String newDeckName) {
        IDeckModifiable deck = this._getDeck(oldDeckName);

        this.removeDeck(oldDeckName);

        deck.setDeckName(newDeckName);

        this.insertDeck(deck);
    }

    private void insertDeck(IDeck deck) {
        this.deck.put(deck.getDeckName(), (IDeckModifiable) deck);
    }

}
