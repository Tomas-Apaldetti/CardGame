package com.core.g3.User;

import com.core.g3.DataBase.IDataBase;
import com.core.g3.User.Exceptions.PasswordDoesntMatchException;
import com.core.g3.User.Exceptions.UserAlreadyExistsException;
import com.core.g3.User.Exceptions.UserDoesntExistException;

public class Register {

    IDataBase DB;

    public Register(IDataBase db) {
        this.DB = db;
    }

    public User createUser(String userName, String password) {
        if (DB.existsUser(userName)) {
            throw new UserAlreadyExistsException();
        }

        DB.createUser(userName, password);

        return new User(userName);
    }

    public User login(String userName, String password) {
        if (!DB.existsUser(userName)) {
            throw new UserDoesntExistException();
        }

        UserDB usrDB = DB.getUser(userName);

        if (!usrDB.getPassword().equals(password)) {
            throw new PasswordDoesntMatchException();
        }

        return new User(usrDB.getUserName());
    }

}
