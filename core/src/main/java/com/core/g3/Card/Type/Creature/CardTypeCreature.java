package com.core.g3.Card.Type.Creature;

import java.util.Arrays;
import java.util.List;

import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Zone.ActiveZoneType;

public class CardTypeCreature extends CardType {

    private final Amount baseHP;
    private final List<Attribute> attributes;
    private final List<IAttack> attacks;

    public CardTypeCreature(Amount baseHP, List<Attribute> attributes, List<IAttack> attacks) {
        super(ICardType.CardType.Creature, Arrays.asList(ActiveZoneType.Combat, ActiveZoneType.Reserve));
        this.baseHP = baseHP;
        this.attributes = attributes;
        this.attacks = attacks;
    }

    public CardTypeCreature(Amount baseHP, List<Attribute> attributes, List<IAttack> attacks,
            List<ActiveZoneType> allowedZones) {
        super(ICardType.CardType.Creature, allowedZones);
        this.baseHP = baseHP;
        this.attributes = attributes;
        this.attacks = attacks;
    }
}
