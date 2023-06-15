package com.core.g3.Card.Cards;

import java.util.ArrayList;
import java.util.Optional;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Cost.CostEnergy;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Reaction.DuplicateReaction;
import com.core.g3.Card.Reaction.IReaction;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Inventor {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Inventor);

        builder.invocationCost.addEnergyCost(EnergyType.Water, new Amount(1));

        ArrayList<Attribute> creatureAttributes = new ArrayList<>();
        ArrayList<IAttack> creatureAttacks = new ArrayList<>();
        builder.cardTypeBuilder.setTypeCreature(new Amount(2), creatureAttributes, creatureAttacks);

        ICost costEnergy = new CostEnergy(Optional.of(EnergyType.Water), new Amount(1));
        IReaction duplicateReaction = new DuplicateReaction();
        builder.cardTypeBuilder.setTypeReaction(costEnergy, duplicateReaction);

        return builder.build();
    }
}
