package com.core.g3.Match.CardInGame.CardTypeStateManager;

import com.core.g3.Card.Attack.IAttack;

import java.util.List;

public class AttackStateManager {

    private final List<IAttack> attacks;

    private boolean alreadyMade;

    public AttackStateManager(List<IAttack> attacks){
        this.attacks = attacks;
        this.alreadyMade = false;
    }

    public boolean canAttack(int idx){
        return !this.attacks.isEmpty() && idx < this.attacks.size() && !this.alreadyMade;
    }
    public void reset(){
        this.alreadyMade = false;
    }

    public void deplete(){
        this.alreadyMade = true;
    }
}
