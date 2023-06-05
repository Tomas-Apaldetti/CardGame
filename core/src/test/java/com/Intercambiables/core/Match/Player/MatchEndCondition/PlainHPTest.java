package com.Intercambiables.core.Match.Player.MatchEndCondition;

import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Match.Player.MatchEndCondition.Exception.HPAlreadyDepletedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlainHPTest {

    @Test
    void receiveDamageAndStillAliveIsNotDepleted() {
        IMatchEndCondition hp = new PlainHP(new Amount(10));
        hp.modify(new Amount(4));

        assertEquals(false, hp.isMet());
    }

    @Test
    void receiveDamageAndDeadIsDepleted() {
        IMatchEndCondition hp = new PlainHP(new Amount(4));
        hp.modify(new Amount(4));

        assertEquals(true, hp.isMet());
    }

    @Test
    void receiveOverkillDamageIsDepleted() {
        IMatchEndCondition hp = new PlainHP(new Amount(4));
        hp.modify(new Amount(400));

        assertEquals(true, hp.isMet());
    }

    @Test
    void receiveDamageThreeTimesLifeNotDepleted() {
        IMatchEndCondition hp = new PlainHP(new Amount(400));
        hp.modify(new Amount(10));
        hp.modify(new Amount(10));
        hp.modify(new Amount(10));

        assertEquals(false, hp.isMet());
    }

    @Test
    void receiveDamageWhileDepletedThrows() {
        IMatchEndCondition hp = new PlainHP(new Amount(20));
        hp.modify(new Amount(10));
        hp.modify(new Amount(400));

        assertThrows(HPAlreadyDepletedException.class, () -> hp.modify(new Amount(10)) );
    }

    @Test
    void numericHPIsTheSubtraction(){
        IMatchEndCondition hp = new PlainHP(new Amount(20));
        hp.modify(new Amount(10));

        assertEquals(10, hp.getNumeric());
    }

    @Test
    void numericHPOnOverkillIsZero(){
        IMatchEndCondition hp = new PlainHP(new Amount(20));
        hp.modify(new Amount(100));

        assertEquals(0, hp.getNumeric());
    }
}