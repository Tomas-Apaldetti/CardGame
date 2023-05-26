package com.Intercambiables.core.User;

public class UserDB {

    private final String userName;
    private final String password;

    public UserDB(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return this.userName;
    }

}
