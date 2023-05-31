package com.Intercambiables.core.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.Intercambiables.core.DataBase.MemoryDataBase;
import com.Intercambiables.core.User.Exceptions.PasswordDoesntMatchException;
import com.Intercambiables.core.User.Exceptions.UserAlreadyExistsException;
import com.Intercambiables.core.User.Exceptions.UserDoesntExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
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

        User usr = reg.createUser("caro", "caro&fran");
        assertThrows(UserAlreadyExistsException.class, () -> reg.createUser("caro", "caro&fran"));
    }

    @Test
    public void login() {
        Register reg = new Register(new MemoryDataBase());

        User usr = reg.createUser("caro", "caro&fran");

        User logged = reg.login("caro", "caro&fran");

        assertEquals("caro", logged.getUserName());
    }

    @Test
    public void loginForNonExistentUserFails() {
        Register reg = new Register(new MemoryDataBase());

        User usr = reg.createUser("caro", "caro&fran");

        assertThrows(UserDoesntExistException.class, () -> reg.login("fran", "caro&fran"));
    }

    @Test
    public void loginForInvalidPasswordFails() {
        Register reg = new Register(new MemoryDataBase());

        User usr = reg.createUser("caro", "caro&fran");

        assertThrows(PasswordDoesntMatchException.class, () -> reg.login("caro", "fran&caro"));
    }

}
