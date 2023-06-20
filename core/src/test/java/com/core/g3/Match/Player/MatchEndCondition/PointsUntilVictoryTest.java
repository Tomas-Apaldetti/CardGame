package com.core.g3.Match.Player.MatchEndCondition;

import com.core.g3.Commons.Amount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointsUntilVictoryTest {

    @Test
    public void pointsIncrementCorrectly() {
        IMatchEndCondition condition = new PointsCounter(new Amount(0), new Amount(10));
        condition.modify(new Amount(2));

        assertEquals(2, condition.getNumeric());

    }

    @Test
    public void pointsConsecutiveIncreasesCorrect() {
        IMatchEndCondition condition = new PointsCounter(new Amount(0), new Amount(10));
        condition.modify(new Amount(2));
        condition.modify(new Amount(1));

        assertEquals(3, condition.getNumeric());
    }

    @Test
    public void pointsConditionMetOk() {
        IMatchEndCondition condition = new PointsCounter(new Amount(0), new Amount(2));
        condition.modify(new Amount(3));

        assertEquals(3, condition.getNumeric());
        assertTrue(condition.isMet());
    }

    @Test
    public void pointsOverflowConditionMetOk() {
        IMatchEndCondition condition = new PointsCounter(new Amount(0), new Amount(2));
        condition.modify(new Amount(2));
        condition.modify(new Amount(4));

        assertEquals(6, condition.getNumeric());
        assertTrue(condition.isMet());
    }

    @Test
    public void pointsCreatedConditionMetOk() {
        IMatchEndCondition condition = new PointsCounter(new Amount(3), new Amount(2));

        assertEquals(3, condition.getNumeric());
        assertTrue(condition.isMet());
    }
}