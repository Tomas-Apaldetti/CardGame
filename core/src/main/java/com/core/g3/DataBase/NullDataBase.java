package com.core.g3.DataBase;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.User.UserDB;

class NullDataBase implements IDataBase {

    public void createUser(String userName, String password) {
        return;
    }

    public boolean existsUser(String userName) {
        return true;
    }

    public UserDB getUser(String userName) {
        return null;
    }

    @Override
    public List<UserDB> getUsers() {
        return new ArrayList<UserDB>();
    }
}
