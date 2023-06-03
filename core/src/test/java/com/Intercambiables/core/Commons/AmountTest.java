package com.Intercambiables.core.Commons;

import com.Intercambiables.core.Market.Exception.InvalidAmountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    public void addPositiveIntoAmountCorrect(){
        Amount a = new Amount(2);

        a.add(1);

        assertEquals(3, a.value());
    }

    @Test
    public void addNegativeAmountThrows(){
        Amount a = new Amount(2);

        assertThrows(InvalidAmountException.class, () -> {
            a.add(-1);
        });
    }

    @Test
    public void addZeroCorrect(){
        Amount a = new Amount(2);

        a.add(0);

        assertEquals(2, a.value());
    }

    @Test
    public void subtractPositiveIntoAmountCorrect(){
        Amount a = new Amount(2);

        a.subtract(1);

        assertEquals(1, a.value());
    }

    @Test
    public void subtractNegativeAmountThrows(){
        Amount a = new Amount(2);

        assertThrows(InvalidAmountException.class, () -> {
            a.subtract(-1);
        });
    }

    @Test
    public void subtractZeroCorrect(){
        Amount a = new Amount(2);

        a.subtract(0);

        assertEquals(2, a.value());
    }

    @Test
    public void subtractMoreThanValueCurrentlyHasThrows() {
        Amount a = new Amount(2);

        assertThrows(InvalidAmountException.class, () -> {
            a.subtract(3);
        });
    }

    @Test
    public void gteCorrectForMinnorValue(){
        Amount a = new Amount(2);
        Amount b = new Amount(1);

        assertEquals(a.gte(b), true);
    }

    @Test
    public void gteCorrectForEqualsValue(){
        Amount a = new Amount(2);
        Amount b = new Amount(2);

        assertEquals(a.gte(b), true);
    }

    @Test
    public void gteCorrectForGratherValue(){
        Amount a = new Amount(2);
        Amount b = new Amount(3);

        assertEquals(a.gte(b), false);
    }

    @Test
    public void subtractOrZeroNonZeroOK(){
        Amount a = new Amount(3);
        Amount b = new Amount(2);

        a.subtractOrZero(b);

        assertEquals(1,a.value() );
    }

    @Test
    public void subtractOrZeroZeroOK(){
        Amount a = new Amount(3);
        Amount b = new Amount(3);

        a.subtractOrZero(b);

        assertEquals(0,a.value() );
    }

    @Test
    public void subtractOrZeroNegativeOK(){
        Amount a = new Amount(3);
        Amount b = new Amount(4);

        a.subtractOrZero(b);

        assertEquals(0,a.value() );
    }
}
