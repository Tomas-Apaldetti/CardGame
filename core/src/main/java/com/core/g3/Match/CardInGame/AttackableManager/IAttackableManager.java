package com.core.g3.Match.CardInGame.AttackableManager;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;

public interface IAttackableManager extends IAttackable {

    boolean isAttackable();

    int current();
}
