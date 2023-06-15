package com.core.g3.DataBase;

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
}
