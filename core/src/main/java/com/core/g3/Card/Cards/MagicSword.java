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

public class MagicSword {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.MagicSword);

        builder.invocationCost.addEnergyCost(EnergyType.Fire, new Amount(1));

        Amount baseHp = new Amount(3);
        ArrayList<Attribute> creatureAttributes = new ArrayList<>();
        creatureAttributes.add(Attribute.Magic);
        creatureAttributes.add(Attribute.Metal);
        ArrayList<IAttack> attacks = new ArrayList<>();
        attacks.add(new SimpleAttack(new Amount(3)));
        builder.cardTypeBuilder.setTypeCreature(baseHp, creatureAttributes, attacks);

        ICost costEnergy = new CostEnergy(Optional.of(EnergyType.Fire), new Amount(1));
        IAction action = new DamageAction(new Amount(3));
        builder.cardTypeBuilder.setTypeAction(costEnergy, action);

        return builder.build();
    }
}
