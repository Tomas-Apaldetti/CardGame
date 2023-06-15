package com.core.g3.Card.Cards;

import java.util.ArrayList;
import java.util.Optional;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Action.DamageAction;
import com.core.g3.Card.Action.IAction;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Attack.SimpleAttack;
import com.core.g3.Card.Cost.CostEnergy;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Orc {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Orc);

        builder.invocationCost.addEnergyCost(EnergyType.Plant, new Amount(1));

        Amount baseHp = new Amount(5);
        ArrayList<Attribute> creatureAttributes = new ArrayList<>();
        ArrayList<IAttack> creatureAttacks = new ArrayList<>();
        creatureAttacks.add(new SimpleAttack(new Amount(3)));
        builder.cardTypeBuilder.setTypeCreature(baseHp, creatureAttributes, creatureAttacks);

        return builder.build();
    }
}
