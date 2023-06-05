package com.core.DataBase;

import java.util.HashMap;

import com.core.User.UserDB;

public class MemoryDataBase implements IDataBase {

    HashMap<String, UserDB> data;

    public MemoryDataBase() {
        this.data = new HashMap<String, UserDB>();
    }

    @Override
    public void createUser(String userName, String password) {
        this.data.put(userName, new UserDB(userName, password));
    }

    @Override
    public boolean existsUser(String userName) {
        return this.data.containsKey(userName);
    }

    @Override
    public UserDB getUser(String userName) {
        return this.data.get(userName);
    }

}
