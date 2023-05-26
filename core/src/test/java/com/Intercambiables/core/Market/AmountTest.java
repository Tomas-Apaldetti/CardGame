package com.Intercambiables.core.Market;


import com.Intercambiables.core.Market.Exception.InvalidAmountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AmountTest {

    @Test
    public void amountWithPositiveValueIsCreated(){
        Amount a = new Amount(2);

        assertNotEquals(a, null);
    }

    @Test
    public void amountWithNegativeValuesThrows(){
        assertThrows(InvalidAmountException.class, () -> {
           new Amount(-2);
        });
    }
}
