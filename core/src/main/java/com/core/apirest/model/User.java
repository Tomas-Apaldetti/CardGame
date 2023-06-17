package com.core.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    public String username;
    @JsonIgnore
    public String password;
    @JsonIgnore
    private int money;

    public User() {
        this.money = 0;
    }

    public void setCredentials(final UserCredentials userCredentials) {
        this.username = userCredentials.username;
        this.password = userCredentials.password;
    }

    public boolean matchCredentials(final UserCredentials userCredentials) {
        if (this.username.equals(userCredentials.username)
                && this.password.equals(userCredentials.password)) {
            return true;
        }
        return false;
    }

    public int getMoney() {
        return money;
    }

    public int setMoney(final int money) {
        this.money = money;
        return this.money;
    }

    public int addMoney(final int money) {
        this.money += money;
        return this.money;
    }
}
