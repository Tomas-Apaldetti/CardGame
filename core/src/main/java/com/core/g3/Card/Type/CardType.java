package com.core.g3.Card.Type;

import java.util.Arrays;
import java.util.List;

import com.core.g3.Card.Action.Exceptions.ActionNotUsableException;
import com.core.g3.Card.Artefact.Exceptions.ArtefactNotUsableException;
import com.core.g3.Card.Attack.Exceptions.CardCantAttackException;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Reaction.Exceptions.ReactionNotUsableException;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZoneType;

public abstract class CardType implements ICardType {

    protected CardTypeName type;
    protected List<ActiveZoneType> allowedZones;

    protected CardType(CardTypeName type) {
        this.type = type;
        this.allowedZones = Arrays.asList(ActiveZoneType.Temporal);
    }

    protected CardType(CardTypeName type, List<ActiveZoneType> allowedZones) {
        this.type = type;
        allowedZones.add(ActiveZoneType.Temporal);
        this.allowedZones = allowedZones;
    }

    @Override
    public CardTypeName getType() {
        return this.type;
    }

    @Override
    public boolean isSummonableIn(ActiveZoneType zoneType) {
        return this.allowedZones.contains(zoneType);
    }

    @Override
    public List<ActiveZoneType> getAllowableZones() {
        return this.allowedZones;
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
    public OriginalAction action(OriginalAction action, IAttackable affected, Player user, Player rival) {
        throw new ActionNotUsableException();
    }

    @Override
    public List<Attribute> getAttributes() {
        return null;
    }

    @Override
    public void reaction(CardInGame cardInGame, ResolutionStack stack, Player user, Player rival) {
        throw new ReactionNotUsableException();
    }
}
