package com.core.g3.Card.Cards;

import java.util.ArrayList;
import java.util.Optional;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Cost.CostEnergy;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Reaction.IReaction;
import com.core.g3.Card.Reaction.SabotageReaction;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Saboteur {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Saboteur);

        builder.invocationCost.addEnergyCost(EnergyType.Water, new Amount(1));

        Amount baseHp = new Amount(3);
        ArrayList<Attribute> creatureAttributes = new ArrayList<>();
        ArrayList<IAttack> creatureAttacks = new ArrayList<>();
        builder.cardTypeBuilder.setTypeCreature(baseHp, creatureAttributes, creatureAttacks);

        ICost costEnergy = new CostEnergy(Optional.of(EnergyType.Water), new Amount(1));
        IReaction sabotageReaction = new SabotageReaction();
        builder.cardTypeBuilder.setTypeReaction(costEnergy, sabotageReaction);

        return builder.build();
    }
}
