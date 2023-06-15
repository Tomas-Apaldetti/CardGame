package com.core.g3.Match.CardInGame;

import com.core.g3.Card.Action.Exceptions.ActionNotUsableException;
import com.core.g3.Card.Artefact.IArtefactEffect;
import com.core.g3.Card.Artefact.Exceptions.ArtefactNotUsableException;
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
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZone;

import java.util.List;
import java.util.Optional;

public class CardInGame implements IAttackable {

    private final ICard base;
    private final Player owner;
    private final AttackStateManager attackState;
    private final OnceManager<IReaction> reactionState;
    private OnceManager<IArtefactEffect> artefactState;
    private ActiveZone currentZone;
    private final IAttackableManager health;

    public CardInGame(Player owner, ICard base, ActiveZone summoningZone) {
        this.base = base;
        this.health = this.base.getHealth();

        this.attackState = new AttackStateManager(this.base.getAttacks());
        this.attackState.deplete();

        this.artefactState = new OnceManager<IArtefactEffect>(this.base.getArtefactEffects());
        this.artefactState.deplete();

        this.reactionState = new OnceManager<IReaction>(this.base.getReactionEffects());
        this.reactionState.deplete();

        this.owner = owner;
    }

    public void discard() {
        this.currentZone.remove(this);
        this.owner.discard(this.base);
    }

    public ICard getBase() {
        return this.base;
    }

    public void moveTo(ActiveZone zone) {
        this.currentZone.remove(this);
        this.currentZone = zone;
        this.currentZone.addCard(this);
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

    public OriginalAction artefact(Player user, Player rival) {
        if (!this.artefactState.canActivate()) {
            throw new ArtefactNotUsableException();
        }
        this.artefactState.deplete();
        return this.base.artefact(new OriginalAction(this), user, rival);
    }

    public OriginalAction artefact(IAttackable affected, Player user, Player rival) {
        if (!this.artefactState.canActivate()) {
            throw new ArtefactNotUsableException();
        }
        this.artefactState.deplete();
        return this.base.artefact(new OriginalAction(this), affected, user, rival);
    }

    public OriginalAction action(Player user, Player rival) {
        if (!rival.isAttackable()) { // @TODO -> repeat for attack & artefact
            throw new ActionNotUsableException();
        }
        return this.base.action(new OriginalAction(this), user, rival);
    }

    public OriginalAction action(IAttackable victim, Player user, Player rival) {
        if (!victim.isAttackable()) {
            throw new ActionNotUsableException();
        }
        return this.base.action(new OriginalAction(this), victim, user, rival);
    }

    public void reaction(Player user, Player rival, ResolutionStack stack) {
        this.base.reaction(this, user, rival, stack);
    }

    public void refreshUse() {
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

    public int getHealth() {
        return this.health.current();
    }

    public Optional<List<Attribute>> getCreatureAttributes() {
        return this.base.getCreatureAttributes();
    }

    public boolean isInActiveZone() {
        return this.currentZone.countsAsActive();
    }
}
