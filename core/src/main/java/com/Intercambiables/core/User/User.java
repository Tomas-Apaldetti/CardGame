package com.Intercambiables.core.User;

import java.util.HashMap;

import com.Intercambiables.core.Deck.Deck;
import com.Intercambiables.core.Deck.IDeck;
import com.Intercambiables.core.Deck.Exceptions.DeckAlreadyExistsException;

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

        this.deck.put(deckName, deck);

        return deck;
    }

    private boolean existsDeck(String deckName) {
        return this.deck.containsKey(deckName);
    }

}
