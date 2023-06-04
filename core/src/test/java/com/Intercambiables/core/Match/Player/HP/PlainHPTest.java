package com.Intercambiables.core.Match.Player.HP;

import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Match.IAccount;
import com.Intercambiables.core.Match.Player.HP.Exception.HPAlreadyDepletedException;
import com.Intercambiables.core.User.TestUserRegister;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlainHPTest {

    @Test
    void receiveDamageAndStillAliveIsNotDepleted() {
        IHP hp = new PlainHP(new Amount(10));
        hp.receiveDamage(new Amount(4));

        assertEquals(false, hp.isDepleted());
    }

    @Test
    void receiveDamageAndDeadIsDepleted() {
        IHP hp = new PlainHP(new Amount(4));
        hp.receiveDamage(new Amount(4));

        assertEquals(true, hp.isDepleted());
    }

    @Test
    void receiveOverkillDamageIsDepleted() {
        IHP hp = new PlainHP(new Amount(4));
        hp.receiveDamage(new Amount(400));

        assertEquals(true, hp.isDepleted());
    }

    @Test
    void receiveDamageThreeTimesLifeNotDepleted() {
        IHP hp = new PlainHP(new Amount(400));
        hp.receiveDamage(new Amount(10));
        hp.receiveDamage(new Amount(10));
        hp.receiveDamage(new Amount(10));

        assertEquals(false, hp.isDepleted());
    }

    @Test
    void receiveDamageWhileDepletedThrows() {
        IHP hp = new PlainHP(new Amount(20));
        hp.receiveDamage(new Amount(10));
        hp.receiveDamage(new Amount(400));

        assertThrows(HPAlreadyDepletedException.class, () -> hp.receiveDamage(new Amount(10)) );
    }

    @Test
    void numericHPIsTheSubtraction(){
        IHP hp = new PlainHP(new Amount(20));
        hp.receiveDamage(new Amount(10));

        assertEquals(10, hp.getNumeric());
    }

    @Test
    void numericHPOnOverkillIsZero(){
        IHP hp = new PlainHP(new Amount(20));
        hp.receiveDamage(new Amount(100));

        assertEquals(0, hp.getNumeric());
    }
}