package com.core.apirest.model;

import com.core.g3.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserAPI {
    public String username;
    @JsonIgnore
    private int money;
    @JsonIgnore
    public User user;

    public UserAPI(User user) {
        this.user = user;
        this.username = user.getUserName();
        this.money = user.getFounds();
    }
}
