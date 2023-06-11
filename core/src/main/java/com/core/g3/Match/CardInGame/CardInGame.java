package com.core.g3.Match.CardInGame;

import com.core.g3.Card.Attack.Exceptions.CardCantAttackException;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.AttackStateManager.AttackStateManager;
import com.core.g3.Match.CardInGame.AttackableManager.IAttackableManager;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZone;

public class CardInGame implements IAttackable {

    private final ICard base;
    private final Player owner;
    private final AttackStateManager attackState;
    private boolean artefactUsed;
    private boolean reactionUsed;
    private ActiveZone currentZone;
    private final IAttackableManager health;

    public CardInGame(Player owner,  ICard base, ActiveZone summoningZone){
        this.base = base;
        this.health = this.base.getHealth();
        this.attackState = new AttackStateManager(this.base.getAttacks());
        this.attackState.deplete();
        this.owner = owner;
        this.currentZone = summoningZone;
        this.refreshUse();
    }
    public void discard(){
        this.currentZone.remove(this);
        this.owner.discard(this.base);
    }

    public ICard getBase(){
        return this.base;
    }

    public void moveTo(ActiveZone zone){
        this.currentZone.remove(this);
        this.currentZone = zone;
        this.currentZone.addCard(this);
    }

    public void attack(CardInGame victim, Player user, Player rival, Amount which){
        if(this.attackState.canAttack(which.value())){
            throw new CardCantAttackException();
        }
        this.base.attack(victim, user, rival, which.value());
        this.attackState.deplete();
    }

    public void refreshUse(){
        this.attackState.reset();
        this.artefactUsed = false;
        this.reactionUsed = false;
    }

    @Override
    public void receiveAttack(Amount damage) {
        this.health.receiveAttack(damage);
    }

    @Override
    public void destroy() {
        this.health.destroy();
    }

    @Override
    public void heal(Amount heal) {
        this.health.heal(heal);
    }
}
