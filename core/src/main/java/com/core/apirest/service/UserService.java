package com.core.apirest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.core.apirest.model.User;
import com.core.apirest.model.UserCredentials;
import com.core.apirest.model.UserMoney;

@Component
public class UserService {
    private List<User> users = new ArrayList<>();

    public void addUser(UserCredentials userCredentials) {
        User newUser = new User();
        newUser.setCredentials(userCredentials);
        users.add(newUser);
    }

    public User getUser(String username) {
        return users.stream().filter(user -> user.username.equals(username)).findFirst().orElse(null);
    }

    public User getUser(UserCredentials userCredentials) {
        return users.stream()
                .filter(user -> user.matchCredentials(userCredentials))
                .findFirst().orElse(null);
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean existsUser(UserCredentials userCredentials) {
        if (this.getUser(userCredentials) != null) {
            return true;
        }
        return false;
    }

    public UserMoney addMoney(String userName, int money) {
        User user = this.getUser(userName);
        int userMoney = user.addMoney(money);
        UserMoney userMoneyDTO = new UserMoney(userName, userMoney);
        return userMoneyDTO;
    }

    public UserMoney getUserMoney(String extractedUsername) {
        User user = this.getUser(extractedUsername);
        int userMoney = user.getMoney();
        UserMoney userMoneyDTO = new UserMoney(extractedUsername, userMoney);
        return userMoneyDTO;
    }
}
