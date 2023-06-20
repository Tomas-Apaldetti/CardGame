package com.core.g3.Match.CardInGame;

import com.core.g3.Card.Action.Exceptions.ActionNotUsableException;
import com.core.g3.Card.Artifact.IArtifactEffect;
import com.core.g3.Card.Artifact.Exceptions.ArtifactNotUsableException;
import com.core.g3.Card.Attack.Exceptions.CantAttackToVictimException;
import com.core.g3.Card.Attack.Exceptions.CardCantAttackException;
import com.core.g3.Card.Reaction.IReaction;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardTypeStateManager.OnceManager;
import com.core.g3.Match.CardInGame.CardTypeStateManager.AttackStateManager;
import com.core.g3.Match.CardInGame.AttackableManager.IAttackableManager;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardInGame implements IAffectable, IDeathPub {

    private final ICard base;
    private final Player owner;
    private AttackStateManager attackState;
    private OnceManager<IReaction> reactionState;
    private OnceManager<IArtifactEffect> artifactState;
    private final List<IDeathSub> interested = new ArrayList<>();
    private final List<IDeathSub> subRemovalsPending = new ArrayList<>();
    private ActiveZone currentZone;
    private IAttackableManager health;

    public CardInGame(Player owner, ICard base, ActiveZone summoningZone) {
        this.base = base;

        this.initFromBase();

        this.currentZone = summoningZone;

        this.interested.add(summoningZone);

        this.owner = owner;
    }

    private void initFromBase(){
        this.health = this.base.getHealth();

        this.attackState = new AttackStateManager(this.base.getAttacks());
        this.attackState.reset();

        this.artifactState = new OnceManager<>(this.base.getArtifactEffects());
        this.artifactState.deplete();

        this.reactionState = new OnceManager<>(this.base.getReactionEffects());
        this.reactionState.deplete();
    }
    public void putInDiscard(){
        this.owner.discard(this.base);
    }
    public void discard() {
        this.currentZone.remove(this);
    }

    public ICard getBase() {
        return this.base;
    }

    public OriginalAction attack(IAttackable victim, Player user, Player rival, Amount with) {
        if (!this.attackState.canAttack(with.value())) {
            throw new CardCantAttackException();
        }

        if (!victim.isAttackable()) {
            throw new CantAttackToVictimException();
        }

        this.attackState.deplete();
        return this.base.attack(new OriginalAction(this), victim, user, rival, with.value());
    }

    public OriginalAction artifact(Player user, Player rival) {
        if (!this.artifactState.canActivate()) {
            throw new ArtifactNotUsableException();
        }
        this.artifactState.deplete();
        return this.base.artifact(new OriginalAction(this), user, rival);
    }

    public OriginalAction artifact(List<IAffectable> affected, Player user, Player rival) {
        if (!this.artifactState.canActivate()) {
            throw new ArtifactNotUsableException();
        }
        this.artifactState.deplete();
        return this.base.artifact(new OriginalAction(this), affected, user, rival);
    }

    public OriginalAction action(Player user, Player rival) {
        if (!rival.isAttackable()) {
            throw new ActionNotUsableException();
        }
        return this.base.action(new OriginalAction(this), user, rival);
    }

    public OriginalAction action(List<IAffectable> victim, Player user, Player rival) {
        if (victim.stream().anyMatch(v -> !v.isAttackable())) {
            throw new ActionNotUsableException();
        }
        return this.base.action(new OriginalAction(this), victim, user, rival);
    }

    public void reaction(Player user, Player rival, ResolutionStack stack) {
        this.base.reaction(this, user, rival, stack);
    }

    public void refreshUse() {
        this.attackState.reset();
        this.artifactState.reset();
        this.reactionState.reset();
    }

    private void notifyDeath(){
        this.removeSubsPending();
        this.interested.forEach(i -> i.notify(this));
    }

    public int getHealth() {
        return this.health.current();
    }

    public Optional<List<Attribute>> getCreatureAttributes() {
        return this.base.getCreatureAttributes();
    }

    public boolean isInActiveZone() {
        return this.currentZone.countsAsActive();
    }

    public void changeZone(ActiveZone activeZone) {
        this.remove(this.currentZone);
        this.currentZone = activeZone;
        this.add(this.currentZone);
    }

    @Override
    public void receiveAttack(Amount damage) {
        this.health.receiveAttack(damage);
        if(this.health.isDead()){
            this.notifyDeath();
        }
    }

    @Override
    public void destroy() {
        this.health.destroy();
        this.notifyDeath();
    }

    @Override
    public void heal(Amount heal) {
        this.health.heal(heal);
    }


    @Override
    public void add(IDeathSub sub) {
        this.removeSubsPending();
        this.interested.add(sub);
    }

    @Override
    public void remove(IDeathSub sub) {
        this.subRemovalsPending.add(sub);
    }

    private void removeSubsPending(){
        this.subRemovalsPending.forEach(s-> this.interested.remove(s));
        this.subRemovalsPending.clear();
    }

    @Override
    public boolean canBeMoved() {
        return true;
    }

    @Override
    public ActiveZoneType currentPlace() {
        return this.currentZone.getType();
    }

    @Override
    public Player getOwner(){
        return this.owner;
    }

    @Override
    public void transferTo(ActiveZone zone) {
        this.currentZone.remove(this);
        this.changeZone(zone);
        this.currentZone.addCard(this);
    }
}
