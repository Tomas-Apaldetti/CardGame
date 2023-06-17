package com.core.apirest.model;

public class User {

    private String username;
    private String password;
    private int money;

    public User() {
        this.username = "";
        this.password = "";
        this.money = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getMoney() {
        return money;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public int setMoney(final int money) {
        this.money = money;
        return this.money;
    }

    public int addMoney(final int money) {
        this.money += money;
        return this.money;
    }

    @Override
    public final String toString() {
        return "User [username=" + username + ", password=" + password + "]";
    }

}
