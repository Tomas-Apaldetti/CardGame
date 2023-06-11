package com.core.g3.Match.CardInGame.AttackStateManager;

import com.core.g3.Card.Attack.Exceptions.CardCantAttackException;
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
        if (this.attacks.isEmpty() ||idx >= this.attacks.size() || this.alreadyMade){
            return false;
        }
        return true;
    }
    public void reset(){
        this.alreadyMade = false;
    }

    public void deplete(){
        this.alreadyMade = true;
    }
}
