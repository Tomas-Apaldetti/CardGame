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

public class Resonance {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Resonance);

       
        ICost costEnergy = new CostEnergy(Optional.of(EnergyType.Fire), new Amount(1),
            Optional.of(new CostEnergy(Optional.of(EnergyType.Fire), new Amount(1))));

        IAction action = new DestroyAction();
        builder.cardTypeBuilder.setTypeAction(costEnergy, action);

        return builder.build();
    }
}
