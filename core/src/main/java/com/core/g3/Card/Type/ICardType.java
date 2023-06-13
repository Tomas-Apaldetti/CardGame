package com.core.g3.Card.Type;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Type.Action.CardTypeAction;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.Zone.ActiveZoneType;

import java.util.List;

public interface ICardType {
    public enum CardType {
        Creature,
        Artefact,
        Action,
        Reaction,
    }

    boolean isArtefact();

    boolean isAction();

    CardType getType();

    boolean isSummonableIn(ActiveZoneType zoneType);

    boolean canAttack();

    OriginalAction attack(OriginalAction action, IAttackable victim, Player user, Player rival, int idx);

    OriginalAction artefact(OriginalAction action, Player user, Player rival);

    OriginalAction artefact(OriginalAction action, IAttackable affected, Player user, Player rival);

    OriginalAction action(OriginalAction action, Player user, Player rival);

    OriginalAction action(OriginalAction action, List<IAttackable> victims, Player user, Player rival);

    List<Attribute> getAttributes();

    ICost getEnergyCost();
}
