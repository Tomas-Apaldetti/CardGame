package com.Intercambiables.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.Intercambiables.core.DataBase.MemoryDataBase;
import com.Intercambiables.core.User.Register;
import com.Intercambiables.core.User.User;
import com.Intercambiables.core.User.Exceptions.PasswordDoesntMatchException;
import com.Intercambiables.core.User.Exceptions.UserAlreadyExistsException;
import com.Intercambiables.core.User.Exceptions.UserDoesntExistException;

@SpringBootTest
public class RegisterTest {

    @Test
    public void createUser() {
        Register reg = new Register(new MemoryDataBase());

        User usr = reg.createUser("caro", "caro&fran");

        assertEquals("caro", usr.getUserName());
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void createExistentUserFails() {
        Register reg = new Register(new MemoryDataBase());

        User usr = reg.createUser("caro", "caro&fran");
        reg.createUser("caro", "caro&fran");
    }

    @Test
    public void login() {
        Register reg = new Register(new MemoryDataBase());

        User usr = reg.createUser("caro", "caro&fran");

        User logged = reg.login("caro", "caro&fran");

        assertEquals("caro", logged.getUserName());
    }

    @Test(expected = UserDoesntExistException.class)
    public void loginForNonExistentUserFails() {
        Register reg = new Register(new MemoryDataBase());

        User usr = reg.createUser("caro", "caro&fran");

        reg.login("fran", "caro&fran");
    }

    @Test(expected = PasswordDoesntMatchException.class)
    public void loginForInvalidPasswordFails() {
        Register reg = new Register(new MemoryDataBase());

        User usr = reg.createUser("caro", "caro&fran");

        reg.login("caro", "fran&caro");
    }

}
