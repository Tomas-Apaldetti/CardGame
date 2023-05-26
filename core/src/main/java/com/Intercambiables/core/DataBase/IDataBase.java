package com.Intercambiables.core.DataBase;

import com.Intercambiables.core.User.UserDB;

public interface IDataBase {
    public void createUser(String userName, String password);

    public boolean existsUser(String userName);

    public UserDB getUser(String userName);
}
