package com.core.g3.Match.CardInGame.AttackableManager;

import com.core.g3.Commons.Amount;

public class Health implements IAttackableManager{

    private final Amount maxHealth;
    private Amount current;

    public Health(int max){
        this.maxHealth = new Amount(max);
        this.current = new Amount(max);
    }
    @Override
    public void receiveAttack(Amount damage) {
        this.current.subtract(damage);
    }

    @Override
    public void destroy() {
        this.current.subtractOrZero(this.maxHealth);
    }

    @Override
    public void heal(Amount heal) {
        this.current.add(heal);
        if(this.current.gt(this.maxHealth)){
            this.current = new Amount(this.maxHealth.value());
        }
    }

    @Override
    public boolean isAttackable() {
        return true;
    }
}
