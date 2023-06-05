package com.core.User;

import org.junit.jupiter.api.Test;

import com.core.DataBase.MemoryDataBase;
import com.core.User.Exceptions.PasswordDoesntMatchException;
import com.core.User.Exceptions.UserAlreadyExistsException;
import com.core.User.Exceptions.UserDoesntExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegisterTest {

    @Test
    public void createUser() {
        Register reg = new Register(new MemoryDataBase());

        User usr = reg.createUser("caro", "caro&fran");

        assertEquals("caro", usr.getUserName());
    }

    @Test
    public void createExistentUserFails() {
        Register reg = new Register(new MemoryDataBase());

        reg.createUser("caro", "caro&fran");
        assertThrows(UserAlreadyExistsException.class, () -> reg.createUser("caro", "caro&fran"));
    }

    @Test
    public void login() {
        Register reg = new Register(new MemoryDataBase());

        reg.createUser("caro", "caro&fran");

        User logged = reg.login("caro", "caro&fran");

        assertEquals("caro", logged.getUserName());
    }

    @Test
    public void loginForNonExistentUserFails() {
        Register reg = new Register(new MemoryDataBase());

        reg.createUser("caro", "caro&fran");

        assertThrows(UserDoesntExistException.class, () -> reg.login("fran", "caro&fran"));
    }

    @Test
    public void loginForInvalidPasswordFails() {
        Register reg = new Register(new MemoryDataBase());

        reg.createUser("caro", "caro&fran");

        assertThrows(PasswordDoesntMatchException.class, () -> reg.login("caro", "fran&caro"));
    }

}
