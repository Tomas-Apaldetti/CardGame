package com.core.g3.Card.Type.Creature;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.CardTypeName;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.Zone.ActiveZoneType;

public class CardTypeCreature extends CardType {

    private final Amount baseHP;
    private final List<Attribute> attributes;
    private final List<IAttack> attacks;

    public CardTypeCreature(Amount baseHP, List<Attribute> attributes, List<IAttack> attacks) {
        super(CardTypeName.Creature, Arrays.asList(ActiveZoneType.Combat, ActiveZoneType.Reserve));
        this.baseHP = baseHP;
        this.attributes = attributes;
        this.attacks = attacks;
    }

    public CardTypeCreature(Amount baseHP, List<Attribute> attributes, List<IAttack> attacks,
            List<ActiveZoneType> allowedZones) {
        super(CardTypeName.Creature, allowedZones);
        this.baseHP = baseHP;
        this.attributes = attributes;
        this.attacks = attacks;
    }

    @Override
    public boolean is(CardTypeName cardType) {
        return CardTypeName.Creature == cardType;
    }

    @Override
    public boolean canAttack(){
        return true;
    }

    @Override
    public OriginalAction attack(OriginalAction action, IAttackable victim, Player user, Player rival, int idx) {
        return this.attacks.get(idx).attack(action, victim, user, rival);
    }

    public List<IAttack> getAttacks(){
        return Collections.unmodifiableList(this.attacks);
    }

    public int getBaseHealth(){
        return this.baseHP.value();
    }

    @Override
    public List<Attribute> getAttributes(){
        if(this.attributes.isEmpty()){
            return null;
        }
        return this.attributes;
    }
}
