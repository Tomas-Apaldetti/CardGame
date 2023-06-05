package com.core.DataBase;

import com.core.User.UserDB;

public interface IDataBase {
    public void createUser(String userName, String password);

    public boolean existsUser(String userName);

    public UserDB getUser(String userName);
}
