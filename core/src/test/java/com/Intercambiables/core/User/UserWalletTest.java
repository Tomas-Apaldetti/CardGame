package com.Intercambiables.core.User;

import com.Intercambiables.core.Market.Amount;
import com.Intercambiables.core.Market.Exception.InsufficientMoneyException;
import com.Intercambiables.core.Market.Exception.InvalidAmountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserWalletTest {
    @Test
    public void amountWithPositiveValueIsCreated(){
        UserWallet a = new UserWallet(2);

        assertNotEquals(a, null);
    }

    @Test
    public void amountWithNegativeValuesThrows(){
        assertThrows(InvalidAmountException.class, () -> {
            new UserWallet(-2);
        });
    }

    @Test
    public void addPositiveIntoUserWalletCorrect(){
        UserWallet a = new UserWallet(2);

        a.add(1);

        assertEquals(3, a.money());
    }

    @Test
    public void addNegativeUserWalletThrows(){
        UserWallet a = new UserWallet(2);

        assertThrows(InvalidAmountException.class, () -> {
            a.add(-1);
        });
    }

    @Test
    public void addZeroCorrect(){
        UserWallet a = new UserWallet(2);

        a.add(0);

        assertEquals(2, a.money());
    }

    @Test
    public void subtractPositiveIntoUserWalletCorrect(){
        UserWallet a = new UserWallet(2);

        a.subtract(1);

        assertEquals(1, a.money());
    }

    @Test
    public void subtractNegativeUserWalletThrows(){
        UserWallet a = new UserWallet(2);

        assertThrows(InvalidAmountException.class, () -> {
            a.subtract(-1);
        });
    }

    @Test
    public void subtractZeroCorrect(){
        UserWallet a = new UserWallet(2);

        a.subtract(0);

        assertEquals(2, a.money());
    }

    @Test
    public void subtractMoreThanValueCurrentlyHasThrows() {
        UserWallet a = new UserWallet(2);

        assertThrows(InsufficientMoneyException.class, () -> {
            a.subtract(3);
        });
    }
}
