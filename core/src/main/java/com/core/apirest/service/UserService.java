package com.core.apirest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.core.apirest.model.User;

@Component
public class UserService {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public User getUserWithOutPasswrod(String username) {
        User user = getUser(username);
        if (user == null) {
            return null;
        }
        User u = new User();
        u.setUsername(user.getUsername());
        return u;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<User> getUsersWithoutPasswords() {
        List<User> usersWithoutPasswords = new ArrayList<>();
        // Hide passwords for security reasons
        for (User user : users) {
            User u = new User();
            u.setUsername(user.getUsername());
            usersWithoutPasswords.add(u);
        }
        return usersWithoutPasswords;
    }

    public boolean existsUser(String username) {
        if (users.stream().anyMatch(u -> u.getUsername().equals(username))) {
            return true;
        }
        return false;
    }
}
