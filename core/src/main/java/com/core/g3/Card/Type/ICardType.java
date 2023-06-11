package com.core.g3.Card.Type;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.Zone.ActiveZoneType;

public interface ICardType {
    public enum CardType {
        Creature,
        Artefact,
        Action,
        Reaction,
    }

    CardType getType();

    boolean isSummonableIn(ActiveZoneType zoneType);

    boolean canAttack();

    OriginalAction attack(OriginalAction action, IAttackable victim, Player user, Player rival, int idx);
}
