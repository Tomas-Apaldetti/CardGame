package com.core.apirest.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.core.apirest.model.UserAPI;
import com.core.apirest.model.UserCredentials;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.DataBase.MemoryDataBase;
import com.core.g3.Deck.ICard;
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
            System.out.print(user.getFounds());
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public ICard getCardByName(UserAPI user, CardName name) {
        return user.user.getCards().stream().filter(card -> card.getName().equals(name)).findFirst().orElse(null);
    }

}
