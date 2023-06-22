package com.core.g3.Match.Player.MatchEndCondition;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.MatchEndCondition.Exception.AttackCanNotBeDoneToMatchEndConditionException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;

public interface IMatchEndCondition extends IAffectable {

    IMatchEndCondition modify(Amount value);

    boolean isMet();

    int getNumeric();

    default void receiveAttack(Amount damage) {
        throw new AttackCanNotBeDoneToMatchEndConditionException();
    }

    default void destroy() {
        throw new AttackCanNotBeDoneToMatchEndConditionException();
    }

    default void heal(Amount heal) {
        throw new AttackCanNotBeDoneToMatchEndConditionException();
    }

    @Override
    default boolean canBeMoved() {
        return false;
    }

    @Override
    default ActiveZoneType currentPlace(){
        return null;
    }

    @Override
    default Player getOwner(){
        throw new RuntimeException("Invalid operation over Player: getOwner");
    }

    @Override
    default void transferTo(ActiveZone zone){
        throw new RuntimeException("Invalid operation over Player: transferTo");
    }
}
