package com.core.g3.Match.CardInGame;

import com.core.g3.Card.Artefact.Exceptions.ArtefactNotUsableException;
import com.core.g3.Card.Attack.Exceptions.CardCantAttackException;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardTypeStateManager.OnceManager;
import com.core.g3.Match.CardInGame.CardTypeStateManager.AttackStateManager;
import com.core.g3.Match.CardInGame.AttackableManager.IAttackableManager;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZone;

import java.util.List;
import java.util.Optional;

public class CardInGame implements IAttackable {

    private final ICard base;
    private final Player owner;
    private final AttackStateManager attackState;
    private final OnceManager reactionState;
    private OnceManager artefactState;
    private ActiveZone currentZone;
    private final IAttackableManager health;

    public CardInGame(Player owner,  ICard base, ActiveZone summoningZone){
        this.base = base;
        this.health = this.base.getHealth();

        this.attackState = new AttackStateManager(this.base.getAttacks());
        this.attackState.deplete();

        this.artefactState = new OnceManager(this.base.getArtefactEffects());
        this.artefactState.deplete();

        this.reactionState = new OnceManager(this.base.getReactionEffects());
        this.reactionState.deplete();

        this.owner = owner;
        this.currentZone = summoningZone;
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

    public OriginalAction attack(CardInGame victim, Player user, Player rival, Amount which){
        if(!this.attackState.canAttack(which.value())){
            throw new CardCantAttackException();
        }
        this.attackState.deplete();
        return this.base.attack(victim, user, rival, which.value());
    }

    public OriginalAction artefact(Player user, Player rival){
        if(!this.artefactState.canActivate()){
            throw new ArtefactNotUsableException();
        }
        this.artefactState.deplete();
        return this.base.artefact(user, rival);
    }

    public OriginalAction artefact(IAttackable affected, Player user, Player rival){
        if(!this.artefactState.canActivate()){
            throw new ArtefactNotUsableException();
        }
        this.artefactState.deplete();
        return this.base.artefact(affected, user, rival);
    }

    public void reaction(Player user, Player rival, ResolutionStack stack){
        this.base.reaction(this, user, rival, stack);
    }

    public void refreshUse(){
        this.attackState.reset();
        this.artefactState.reset();
        this.reactionState.reset();
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

    public int getHealth(){
        return this.health.current();
    }

    public Optional<List<Attribute>> getCreatureAttributes(){
        return this.base.getCreatureAttributes();
    }

    public boolean isInActiveZone() {
        return this.currentZone.countsAsActive();
    }
}
