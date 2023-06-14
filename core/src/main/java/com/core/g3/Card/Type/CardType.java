package com.core.g3.Card.Type;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.Card.Action.Exceptions.ActionNotUsableException;
import com.core.g3.Card.Artefact.Exceptions.ArtefactNotUsableException;
import com.core.g3.Card.Attack.Exceptions.CardCantAttackException;
import com.core.g3.Card.Cost.CostEnergy;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Reaction.Exceptions.ReactionNotUsableException;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Card.Type.Exceptions.CardTypeNeedsAtLeastOneEffectException;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZoneType;

public abstract class CardType implements ICardType {

    protected ICardType.CardType type;
    protected List<ActiveZoneType> allowedZones;

    protected CardType(CardType type) {
        this.type = type;
        this.allowedZones = new ArrayList<>();
    }

    protected CardType(CardType type, List<ActiveZoneType> allowedZones) {
        this.type = type;
        this.allowedZones = allowedZones;
    }

    @Override
    public ICardType.CardType getType() {
        return this.type;
    }

    @Override
    public boolean isSummonableIn(ActiveZoneType zoneType) {
        return this.allowedZones.contains(zoneType);
    }

    @Override
    public boolean canAttack() {
        return false;
    }

    @Override
    public OriginalAction attack(OriginalAction action, IAttackable victim, Player user, Player rival, int idx) {
        throw new CardCantAttackException();
    }

    @Override
    public boolean isArtefact() {
        return false;
    }

    @Override
    public boolean isAction() {
        return false;
    }

    @Override
    public OriginalAction artefact(OriginalAction action, Player user, Player rival) {
        throw new ArtefactNotUsableException();
    }

    @Override
    public OriginalAction artefact(OriginalAction action, IAttackable affected, Player user, Player rival) {
        throw new ArtefactNotUsableException();
    }

    @Override
    public OriginalAction action(OriginalAction action, Player user, Player rival) {
        throw new ActionNotUsableException();
    }

    @Override
    public OriginalAction action(OriginalAction action, List<IAttackable> victims, Player user, Player rival) {
        throw new ActionNotUsableException();
    }

    @Override
    public List<Attribute> getAttributes() {
        return null;
    }

    @Override
    public void reaction(CardInGame cardInGame, ResolutionStack stack, Player user, Player rival){
        throw new ReactionNotUsableException();
    }
    
    public ICost getEnergyCost() {
        return new CostEnergy(java.util.Optional.empty(), new Amount(0));
    }
}
