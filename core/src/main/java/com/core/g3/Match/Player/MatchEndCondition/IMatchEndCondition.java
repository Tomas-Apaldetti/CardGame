package com.core.g3.Match.Player.MatchEndCondition;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.MatchEndCondition.Exception.AttackCanNotBeDoneToMatchEndConditionException;

public interface IMatchEndCondition extends IAttackable {

    IMatchEndCondition modify(Amount value);

    boolean isMet();

    int getNumeric();

    default public void receiveAttack(Amount damage) {
        throw new AttackCanNotBeDoneToMatchEndConditionException();
    }

    default public void destroy() {
        throw new AttackCanNotBeDoneToMatchEndConditionException();
    }

    default public void heal(Amount heal) {
        throw new AttackCanNotBeDoneToMatchEndConditionException();
    }
}
