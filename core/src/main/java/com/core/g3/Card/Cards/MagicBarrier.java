package com.core.g3.Card.Cards;

import java.util.ArrayList;
import java.util.Optional;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Cost.CostEnergy;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Reaction.DamageReductionReaction;
import com.core.g3.Card.Reaction.IReaction;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Zone.ActiveZoneType;

public class MagicBarrier {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.MagicBarrier);

        builder.invocationCost.addEnergyCost(EnergyType.Fire, new Amount(1));

        Amount baseHp = new Amount(10);
        ArrayList<Attribute> creatureAttributes = new ArrayList<>();
        creatureAttributes.add(Attribute.Magic);
        ArrayList<ActiveZoneType> allowedZones = new ArrayList<>();
        allowedZones.add(ActiveZoneType.Artifacts);
        allowedZones.add(ActiveZoneType.Reserve);
        builder.cardTypeBuilder.setTypeCreature(baseHp, creatureAttributes, null, allowedZones);

        ICost costEnergy = new CostEnergy(Optional.of(EnergyType.Fire), new Amount(1));
        IReaction reaction = new DamageReductionReaction(new Amount(1));
        builder.cardTypeBuilder.setTypeReaction(costEnergy, reaction);

        return builder.build();
    }
}
