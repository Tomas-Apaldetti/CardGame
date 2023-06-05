package com.core.g3.DataBase;

import com.core.g3.User.UserDB;

public interface IDataBase {
    public void createUser(String userName, String password);

    public boolean existsUser(String userName);

    public UserDB getUser(String userName);
}
