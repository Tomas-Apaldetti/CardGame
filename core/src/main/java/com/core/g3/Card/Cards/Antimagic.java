package com.core.g3.Card.Cards;

import java.util.Optional;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Action.BothMassiveDamageAction;
import com.core.g3.Card.Action.IAction;
import com.core.g3.Card.Artefact.MassiveDamage;
import com.core.g3.Card.Cost.CostEnergy;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;

public class Antimagic {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Antimagic);

        builder.invocationCost.addEnergyCost(new Amount(1));

        MassiveDamage massiveDamageEffect = new MassiveDamage(new Amount(1), Attribute.Magic);
        builder.cardTypeBuilder.setTypeArtefact(massiveDamageEffect);

        ICost costEnergy = new CostEnergy(Optional.empty(), new Amount(1));
        IAction effect = new BothMassiveDamageAction(new Amount(1), Attribute.Magic);

        builder.cardTypeBuilder.setTypeAction(costEnergy, effect);
        return builder.build();
    }
}
