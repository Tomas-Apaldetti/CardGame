package com.core.g3.Card.Attack;

import com.core.g3.Commons.Amount;

public interface IAttackable {

    void receiveAttack(Amount damage);

    void destroy();

    void heal(Amount heal);

    default boolean isDead(){
        return false;
    }

    default boolean isAttackable() {
        return true;
    };

}
