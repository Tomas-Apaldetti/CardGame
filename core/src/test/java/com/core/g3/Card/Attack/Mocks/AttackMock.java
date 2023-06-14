package com.core.g3.Card.Attack.Mocks;

import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Mock.Exceptions.ExpectedException;

public class AttackMock implements IAttack {

    @Override
    public OriginalAction attack(OriginalAction action, IAttackable victim, Player user, Player rival) {
        throw new ExpectedException();
    }
}
