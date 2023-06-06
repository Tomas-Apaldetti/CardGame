package com.core.g3.Card.Type.Creature;

import java.util.List;

import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Commons.Amount;

public class CardTypeCreature extends CardType {

    private final Amount baseHP;
    private final List<Attribute> attributes;
    private final List<IAttack> attacks;

    public CardTypeCreature(Amount baseHP, List<Attribute> attributes, List<IAttack> attacks) {
        this.type = ICardType.CardType.Creature;
        this.baseHP = baseHP;
        this.attributes = attributes;
        this.attacks = attacks;
    }

}
