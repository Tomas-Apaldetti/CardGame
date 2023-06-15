package com.core.g3.Card.Type;

import java.util.List;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZoneType;

public interface ICardType {

    boolean is(CardTypeName cardType);

    CardTypeName getType();

    boolean isSummonableIn(ActiveZoneType zoneType);

    List<ActiveZoneType> getAllowableZones();

    boolean canAttack();

    OriginalAction attack(OriginalAction action, IAttackable victim, Player user, Player rival, int idx);

    OriginalAction artefact(OriginalAction action, Player user, Player rival);

    OriginalAction artefact(OriginalAction action, IAttackable affected, Player user, Player rival);

    void reaction(CardInGame cardInGame, ResolutionStack stack, Player user, Player rival);

    OriginalAction action(OriginalAction action, Player user, Player rival);

    OriginalAction action(OriginalAction action, IAttackable affected, Player user);

    List<Attribute> getAttributes();
}
