package com.core.g3.Card.Cards;

import java.util.Optional;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Action.DrainEnergyAction;
import com.core.g3.Card.Action.IAction;
import com.core.g3.Card.Cost.CostEnergy;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Drain {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Drain);

        ICost cost = new CostEnergy(Optional.of(EnergyType.Water), new Amount(2));
        IAction action = new DrainEnergyAction(new Amount(1));
        builder.cardTypeBuilder.setTypeAction(cost, action);

        return builder.build();
    }
}
