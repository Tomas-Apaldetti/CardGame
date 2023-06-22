package com.core.apirest.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.core.apirest.model.UserAPI;
import com.core.apirest.model.UserCredentials;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.DataBase.MemoryDataBase;
import com.core.g3.Deck.ICard;
import com.core.g3.Deck.IDeck;
import com.core.g3.Deck.Exceptions.CardAlreadyExistsInDeckException;
import com.core.g3.Deck.Exceptions.DeckAlreadyExistsException;
import com.core.g3.Market.Exceptions.InsufficientMoneyException;
import com.core.g3.User.Register;
import com.core.g3.User.User;
import com.core.g3.User.Exceptions.UserDoesntExistException;

@Component
public class UserService {
    private Register reg = new Register(new MemoryDataBase());
    private HashMap<String, User> users = new HashMap<String, User>();

    public void addUser(UserCredentials userCredentials) {
        User user = reg.createUser(userCredentials.username, userCredentials.password);
        users.put(userCredentials.username, user);
    }

    public UserAPI getUser(UserCredentials userCredentials) {
        User user = reg.login(userCredentials.username, userCredentials.password);
        return new UserAPI(user);
    }

    public List<UserAPI> getUsers() {
        return users.values().stream().map(user -> new UserAPI(user)).toList();
    }

    public UserAPI getUser(String userName) {
        User user = users.get(userName);
        return new UserAPI(user);
    }

    public String addMoney(String userName, int money) {
        User user = users.get(userName);

        try {
            user.credit(new Amount(money));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error al agregar dinero";
        }

        return "Dinero agregado";
    }

    public List<String> getCards(String userName) {
        User user = users.get(userName);
        return user.getCards().stream().map(card -> card.getName().toString())
                .toList();
    }

    public String buyCard(String username, String cardName) {
        User user = users.get(username);

        CardName cardNameEnum = CardName.valueOf(cardName);
        try {
            user.buyCards(cardNameEnum, 1);
        } catch (InsufficientMoneyException e) {
            return "Dinero insuficiente para comprar carta";
        } catch (Exception e) {
            return "Error al comprar carta";
        }
        return "Carta comprada";
    }

    public ResponseEntity<Integer> getUserFounds(String extractedUsername) {
        UserAPI user;
        try {
            user = this.getUser(extractedUsername);
        } catch (UserDoesntExistException e) {
            return ResponseEntity.notFound().build();
        }
        Integer founds = user.user.getFounds();

        return ResponseEntity.ok(founds);
    }

    public ResponseEntity<List<String>> getDecks(String username) {
        UserAPI user;
        try {
            user = this.getUser(username);
        } catch (UserDoesntExistException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok(user.user.getDeckInventory().getDecks().stream().map(deck -> deck.getDeckName()).toList());
    }

    public ResponseEntity<String> createDeck(String username, String deckName) {
        UserAPI user;
        try {
            user = this.getUser(username);
        } catch (UserDoesntExistException e) {
            return ResponseEntity.notFound().build();
        }
        try {
            user.user.getDeckInventory().createDeck(deckName);
        } catch (DeckAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Deck ya existe");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear deck");
        }
        return ResponseEntity.ok("Deck creado");
    }

    public ResponseEntity<String> addCardToDeck(String username, String deckName, String cardName) {
        UserAPI user;
        try {
            user = this.getUser(username);
        } catch (UserDoesntExistException e) {
            return ResponseEntity.notFound().build();
        }

        ICard card = this.getCard(cardName, user);
        if (card == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carta no encontrada");
        }

        IDeck deck = this.GetDeck(deckName, user);
        if (deckName == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deck no encontrado");
        }
        // add card to deck
        try {
            deck.addCard(card);
        } catch (CardAlreadyExistsInDeckException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Carta ya existe en deck");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar carta a deck");
        }

        return ResponseEntity.ok("Carta agregada a deck");
    }

    public ResponseEntity<List<String>> getDecksCards(String username, String deckName) {
        UserAPI user;
        try {
            user = this.getUser(username);
        } catch (UserDoesntExistException e) {
            return ResponseEntity.notFound().build();
        }

        IDeck deck = this.GetDeck(deckName, user);
        if (deckName == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<String> cards = deck.getCards().stream().map(card -> card.getName().toString()).toList();

        return ResponseEntity.ok(cards);
    }

    public ResponseEntity<String> removeCardFromDeck(String username, String deckName, String cardName) {
        UserAPI user;
        try {
            user = this.getUser(username);
        } catch (UserDoesntExistException e) {
            return ResponseEntity.notFound().build();
        }
        ICard card = this.getCard(cardName, user);
        if (card == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carta no encontrada");
        }

        IDeck deck = this.GetDeck(deckName, user);
        if (deckName == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deck no encontrado");
        }
        // add card to deck
        try {
            deck.removeCard(card);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al remover carta de deck");
        }

        return ResponseEntity.ok("Carta removida de deck");
    }

    public IDeck GetDeck(String deckName, UserAPI user) {
        IDeck deck = user.user.getDeckInventory().getDecks().stream()
                .filter(deck_ -> deck_.getDeckName().equals(deckName)).findFirst().orElse(null);
        return deck;
    }

    public ICard getCard(String cardName, UserAPI user) {
        CardName cardNameEnum = CardName.valueOf(cardName);
        // get cards from inventory and select card by name
        ICard card = user.user.getCardInventory().getCards().stream().filter(c -> c.getName().equals(cardNameEnum))
                .findFirst().orElse(null);
        return card;
    }

}
