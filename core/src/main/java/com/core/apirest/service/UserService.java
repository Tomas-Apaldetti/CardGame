package com.core.apirest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.core.apirest.model.UserAPI;
import com.core.apirest.model.UserCredentials;
import com.core.g3.Commons.Amount;
import com.core.g3.DataBase.MemoryDataBase;
import com.core.g3.User.Register;
import com.core.g3.User.User;

@Component
public class UserService {
    private Register reg = new Register(new MemoryDataBase());

    public void addUser(UserCredentials userCredentials) {
        reg.createUser(userCredentials.username, userCredentials.password);
    }

    public UserAPI getUser(UserCredentials userCredentials) {
        User user = reg.login(userCredentials.username, userCredentials.password);
        return new UserAPI(user);
    }

    public List<UserAPI> getUsers() {
        return reg.getUsers().stream().map(user -> new UserAPI(user)).toList();
    }

    public UserAPI getUser(String userName) {
        User user = reg.getUser(userName);
        return new UserAPI(user);
    }

    public String addMoney(String userName, int money) {
        User user = reg.getUser(userName);

        try {
            user.credit(new Amount(money));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "Money added";
    }
}
