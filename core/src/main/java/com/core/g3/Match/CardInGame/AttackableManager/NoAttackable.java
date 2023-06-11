package com.core.g3.Match.CardInGame.AttackableManager;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.AttackableManager.Exceptions.EntityIsNotAttackableException;

public class NoAttackable implements IAttackableManager{
    @Override
    public void receiveAttack(Amount damage) {
        throw new EntityIsNotAttackableException();
    }

    @Override
    public void destroy() {
        throw new EntityIsNotAttackableException();
    }

    @Override
    public void heal(Amount heal) {
        throw new EntityIsNotAttackableException();
    }

    @Override
    public boolean isAttackable() {
        return false;
    }
}
