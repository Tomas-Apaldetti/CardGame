package com.core.g3.User;

import java.util.List;

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

    public User getUser(String userName) {
        if (!DB.existsUser(userName)) {
            throw new UserDoesntExistException();
        }

        UserDB usrDB = DB.getUser(userName);

        return new User(usrDB.getUserName());
    }

    public List<User> getUsers() {
        List<UserDB> users = DB.getUsers();
        return users.stream().map(userDB -> new User(userDB.getUserName())).toList();
    }

}
