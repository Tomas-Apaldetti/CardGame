package com.core.DataBase;

import com.core.User.UserDB;

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
}
